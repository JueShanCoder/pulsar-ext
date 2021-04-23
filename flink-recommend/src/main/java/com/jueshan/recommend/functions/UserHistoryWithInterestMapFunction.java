package com.jueshan.recommend.functions;

import com.jueshan.recommend.client.HbaseClient;
import com.jueshan.recommend.entitys.LogEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.api.common.state.StateTtlConfig;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.common.time.Time;
import org.apache.flink.configuration.Configuration;

import java.io.Serializable;

public class UserHistoryWithInterestMapFunction extends RichMapFunction<LogEntity,String> {

    // 根据用户对同一个产品的操作计算兴趣度,计算规则通过操作间隔时间(如购物 - 浏览 < 100s)则判定为一次兴趣事件
    // 通过Flink的ValueState实现,如果用户的操作Action=3(收藏),则清除这个产品的state,如果超过100s没有出现Action=3的事件,也会清除这个state

    private ValueState<Action> state;

    @Override
    public String map(LogEntity logEntity) throws Exception {
        Action actionLastTime = state.value();
        Action actionCurrTime = new Action(logEntity.getAction(), logEntity.getTime().toString());
        int times = 1;
        // 如果用户没有操作，则为state赋值
        if (actionLastTime == null){
            actionLastTime = actionCurrTime;
            saveToHbase(logEntity,times);
        } else {
            times = getTimesByRule(actionLastTime,actionCurrTime);
        }
        saveToHbase(logEntity,times);
        // 如果用户的操作为3(购物),则清除这个key的state
        if (actionCurrTime.getType().equals("3")){
            state.clear();
        }
        return null;
    }

    @Override
    public void open(Configuration parameters) throws Exception {        // 设置 state 的过期时间为100s
        StateTtlConfig ttlConfig = StateTtlConfig
                .newBuilder(Time.seconds(100L))
                // 状态时间戳的更新方式
                .setUpdateType(StateTtlConfig.UpdateType.OnCreateAndWrite)
                // 过期状态数据的可见性
                .setStateVisibility(StateTtlConfig.StateVisibility.NeverReturnExpired)
                .build();

        ValueStateDescriptor<Action> desc = new ValueStateDescriptor<>("Action Time", Action.class);
        desc.enableTimeToLive(ttlConfig);
        state = getRuntimeContext().getState(desc);
    }

    @Override
    public void close() throws Exception {
        super.close();
    }

    private void saveToHbase(LogEntity log, int times) throws Exception {
        if (log != null){
            for (int i=0; i < times; i++) {
                HbaseClient.increamColumn("u_interest",String.valueOf(log.getUserId()),"p",String.valueOf(log.getProductId()));
            }
        }
    }

    private int getTimesByRule(Action actionLastTime, Action actionCurrTime) {
        int a1 = Integer.parseInt(actionLastTime.getType());
        int a2 = Integer.parseInt(actionCurrTime.getType());
        int t1 = Integer.parseInt(actionLastTime.getTime());
        int t2 = Integer.parseInt(actionCurrTime.getTime());
        int pluse = 1;
        // 如果动作连续发生且时间很短（小于100s内完成），则标注用户对此产品兴趣度很高
        if (a2 > a1 && (t2 - t1) < 100_00L){
            pluse *= a2 - a1;
        }
        return pluse;
    }

    /**
     * 动作类、记录动作类型和动作发生时间（Event Time）
     * type （1 浏览、2 分享、3 购物）
     */
    @Setter
    @Getter
    @AllArgsConstructor
    static class Action implements Serializable {
        private String type;
        private String time;
    }

}