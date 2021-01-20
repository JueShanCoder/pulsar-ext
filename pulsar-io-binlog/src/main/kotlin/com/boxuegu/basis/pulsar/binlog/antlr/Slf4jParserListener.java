package com.boxuegu.basis.pulsar.binlog.antlr;

import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Slf4jParserListener extends MySqlParserBaseListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(Slf4jParserListener.class);
    private final Parser parser;

    public Slf4jParserListener(Parser parser) {
        this.parser = parser;
    }

    @Override
    public void enterRoot(MySqlParser.RootContext ctx) {
        LOGGER.debug("enterRoot <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitRoot(MySqlParser.RootContext ctx) {
        LOGGER.debug("exitRoot <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterSqlStatements(MySqlParser.SqlStatementsContext ctx) {
        LOGGER.debug("enterSqlStatements <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitSqlStatements(MySqlParser.SqlStatementsContext ctx) {
        LOGGER.debug("exitSqlStatements <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterSqlStatement(MySqlParser.SqlStatementContext ctx) {
        LOGGER.debug("enterSqlStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitSqlStatement(MySqlParser.SqlStatementContext ctx) {
        LOGGER.debug("exitSqlStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterEmptyStatement(MySqlParser.EmptyStatementContext ctx) {
        LOGGER.debug("enterEmptyStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitEmptyStatement(MySqlParser.EmptyStatementContext ctx) {
        LOGGER.debug("exitEmptyStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterDdlStatement(MySqlParser.DdlStatementContext ctx) {
        LOGGER.debug("enterDdlStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitDdlStatement(MySqlParser.DdlStatementContext ctx) {
        LOGGER.debug("exitDdlStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterDmlStatement(MySqlParser.DmlStatementContext ctx) {
        LOGGER.debug("enterDmlStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitDmlStatement(MySqlParser.DmlStatementContext ctx) {
        LOGGER.debug("exitDmlStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterTransactionStatement(MySqlParser.TransactionStatementContext ctx) {
        LOGGER.debug("enterTransactionStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitTransactionStatement(MySqlParser.TransactionStatementContext ctx) {
        LOGGER.debug("exitTransactionStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterReplicationStatement(MySqlParser.ReplicationStatementContext ctx) {
        LOGGER.debug("enterReplicationStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitReplicationStatement(MySqlParser.ReplicationStatementContext ctx) {
        LOGGER.debug("exitReplicationStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterPreparedStatement(MySqlParser.PreparedStatementContext ctx) {
        LOGGER.debug("enterPreparedStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitPreparedStatement(MySqlParser.PreparedStatementContext ctx) {
        LOGGER.debug("exitPreparedStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterCompoundStatement(MySqlParser.CompoundStatementContext ctx) {
        LOGGER.debug("enterCompoundStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitCompoundStatement(MySqlParser.CompoundStatementContext ctx) {
        LOGGER.debug("exitCompoundStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterAdministrationStatement(MySqlParser.AdministrationStatementContext ctx) {
        LOGGER.debug("enterAdministrationStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitAdministrationStatement(MySqlParser.AdministrationStatementContext ctx) {
        LOGGER.debug("exitAdministrationStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterUtilityStatement(MySqlParser.UtilityStatementContext ctx) {
        LOGGER.debug("enterUtilityStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitUtilityStatement(MySqlParser.UtilityStatementContext ctx) {
        LOGGER.debug("exitUtilityStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterCreateDatabase(MySqlParser.CreateDatabaseContext ctx) {
        LOGGER.debug("enterCreateDatabase <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitCreateDatabase(MySqlParser.CreateDatabaseContext ctx) {
        LOGGER.debug("exitCreateDatabase <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterCreateEvent(MySqlParser.CreateEventContext ctx) {
        LOGGER.debug("enterCreateEvent <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitCreateEvent(MySqlParser.CreateEventContext ctx) {
        LOGGER.debug("exitCreateEvent <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterCreateIndex(MySqlParser.CreateIndexContext ctx) {
        LOGGER.debug("enterCreateIndex <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitCreateIndex(MySqlParser.CreateIndexContext ctx) {
        LOGGER.debug("exitCreateIndex <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterCreateLogfileGroup(MySqlParser.CreateLogfileGroupContext ctx) {
        LOGGER.debug("enterCreateLogfileGroup <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitCreateLogfileGroup(MySqlParser.CreateLogfileGroupContext ctx) {
        LOGGER.debug("exitCreateLogfileGroup <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterCreateProcedure(MySqlParser.CreateProcedureContext ctx) {
        LOGGER.debug("enterCreateProcedure <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitCreateProcedure(MySqlParser.CreateProcedureContext ctx) {
        LOGGER.debug("exitCreateProcedure <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterCreateFunction(MySqlParser.CreateFunctionContext ctx) {
        LOGGER.debug("enterCreateFunction <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitCreateFunction(MySqlParser.CreateFunctionContext ctx) {
        LOGGER.debug("exitCreateFunction <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterCreateServer(MySqlParser.CreateServerContext ctx) {
        LOGGER.debug("enterCreateServer <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitCreateServer(MySqlParser.CreateServerContext ctx) {
        LOGGER.debug("exitCreateServer <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterCopyCreateTable(MySqlParser.CopyCreateTableContext ctx) {
        LOGGER.debug("enterCopyCreateTable <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitCopyCreateTable(MySqlParser.CopyCreateTableContext ctx) {
        LOGGER.debug("exitCopyCreateTable <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterQueryCreateTable(MySqlParser.QueryCreateTableContext ctx) {
        LOGGER.debug("enterQueryCreateTable <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitQueryCreateTable(MySqlParser.QueryCreateTableContext ctx) {
        LOGGER.debug("exitQueryCreateTable <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterColumnCreateTable(MySqlParser.ColumnCreateTableContext ctx) {
        LOGGER.debug("enterColumnCreateTable <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitColumnCreateTable(MySqlParser.ColumnCreateTableContext ctx) {
        LOGGER.debug("exitColumnCreateTable <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterCreateTablespaceInnodb(MySqlParser.CreateTablespaceInnodbContext ctx) {
        LOGGER.debug("enterCreateTablespaceInnodb <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitCreateTablespaceInnodb(MySqlParser.CreateTablespaceInnodbContext ctx) {
        LOGGER.debug("exitCreateTablespaceInnodb <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterCreateTablespaceNdb(MySqlParser.CreateTablespaceNdbContext ctx) {
        LOGGER.debug("enterCreateTablespaceNdb <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitCreateTablespaceNdb(MySqlParser.CreateTablespaceNdbContext ctx) {
        LOGGER.debug("exitCreateTablespaceNdb <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterCreateTrigger(MySqlParser.CreateTriggerContext ctx) {
        LOGGER.debug("enterCreateTrigger <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitCreateTrigger(MySqlParser.CreateTriggerContext ctx) {
        LOGGER.debug("exitCreateTrigger <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterCreateView(MySqlParser.CreateViewContext ctx) {
        LOGGER.debug("enterCreateView <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitCreateView(MySqlParser.CreateViewContext ctx) {
        LOGGER.debug("exitCreateView <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterCreateDatabaseOption(MySqlParser.CreateDatabaseOptionContext ctx) {
        LOGGER.debug("enterCreateDatabaseOption <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitCreateDatabaseOption(MySqlParser.CreateDatabaseOptionContext ctx) {
        LOGGER.debug("exitCreateDatabaseOption <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterOwnerStatement(MySqlParser.OwnerStatementContext ctx) {
        LOGGER.debug("enterOwnerStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitOwnerStatement(MySqlParser.OwnerStatementContext ctx) {
        LOGGER.debug("exitOwnerStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterPreciseSchedule(MySqlParser.PreciseScheduleContext ctx) {
        LOGGER.debug("enterPreciseSchedule <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitPreciseSchedule(MySqlParser.PreciseScheduleContext ctx) {
        LOGGER.debug("exitPreciseSchedule <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterIntervalSchedule(MySqlParser.IntervalScheduleContext ctx) {
        LOGGER.debug("enterIntervalSchedule <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitIntervalSchedule(MySqlParser.IntervalScheduleContext ctx) {
        LOGGER.debug("exitIntervalSchedule <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterTimestampValue(MySqlParser.TimestampValueContext ctx) {
        LOGGER.debug("enterTimestampValue <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitTimestampValue(MySqlParser.TimestampValueContext ctx) {
        LOGGER.debug("exitTimestampValue <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterIntervalExpr(MySqlParser.IntervalExprContext ctx) {
        LOGGER.debug("enterIntervalExpr <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitIntervalExpr(MySqlParser.IntervalExprContext ctx) {
        LOGGER.debug("exitIntervalExpr <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterIntervalType(MySqlParser.IntervalTypeContext ctx) {
        LOGGER.debug("enterIntervalType <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitIntervalType(MySqlParser.IntervalTypeContext ctx) {
        LOGGER.debug("exitIntervalType <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterEnableType(MySqlParser.EnableTypeContext ctx) {
        LOGGER.debug("enterEnableType <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitEnableType(MySqlParser.EnableTypeContext ctx) {
        LOGGER.debug("exitEnableType <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterIndexType(MySqlParser.IndexTypeContext ctx) {
        LOGGER.debug("enterIndexType <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitIndexType(MySqlParser.IndexTypeContext ctx) {
        LOGGER.debug("exitIndexType <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterIndexOption(MySqlParser.IndexOptionContext ctx) {
        LOGGER.debug("enterIndexOption <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitIndexOption(MySqlParser.IndexOptionContext ctx) {
        LOGGER.debug("exitIndexOption <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterProcedureParameter(MySqlParser.ProcedureParameterContext ctx) {
        LOGGER.debug("enterProcedureParameter <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitProcedureParameter(MySqlParser.ProcedureParameterContext ctx) {
        LOGGER.debug("exitProcedureParameter <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterFunctionParameter(MySqlParser.FunctionParameterContext ctx) {
        LOGGER.debug("enterFunctionParameter <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitFunctionParameter(MySqlParser.FunctionParameterContext ctx) {
        LOGGER.debug("exitFunctionParameter <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterRoutineComment(MySqlParser.RoutineCommentContext ctx) {
        LOGGER.debug("enterRoutineComment <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitRoutineComment(MySqlParser.RoutineCommentContext ctx) {
        LOGGER.debug("exitRoutineComment <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterRoutineLanguage(MySqlParser.RoutineLanguageContext ctx) {
        LOGGER.debug("enterRoutineLanguage <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitRoutineLanguage(MySqlParser.RoutineLanguageContext ctx) {
        LOGGER.debug("exitRoutineLanguage <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterRoutineBehavior(MySqlParser.RoutineBehaviorContext ctx) {
        LOGGER.debug("enterRoutineBehavior <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitRoutineBehavior(MySqlParser.RoutineBehaviorContext ctx) {
        LOGGER.debug("exitRoutineBehavior <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterRoutineData(MySqlParser.RoutineDataContext ctx) {
        LOGGER.debug("enterRoutineData <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitRoutineData(MySqlParser.RoutineDataContext ctx) {
        LOGGER.debug("exitRoutineData <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterRoutineSecurity(MySqlParser.RoutineSecurityContext ctx) {
        LOGGER.debug("enterRoutineSecurity <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitRoutineSecurity(MySqlParser.RoutineSecurityContext ctx) {
        LOGGER.debug("exitRoutineSecurity <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterServerOption(MySqlParser.ServerOptionContext ctx) {
        LOGGER.debug("enterServerOption <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitServerOption(MySqlParser.ServerOptionContext ctx) {
        LOGGER.debug("exitServerOption <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterCreateDefinitions(MySqlParser.CreateDefinitionsContext ctx) {
        LOGGER.debug("enterCreateDefinitions <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitCreateDefinitions(MySqlParser.CreateDefinitionsContext ctx) {
        LOGGER.debug("exitCreateDefinitions <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterColumnDeclaration(MySqlParser.ColumnDeclarationContext ctx) {
        LOGGER.debug("enterColumnDeclaration <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitColumnDeclaration(MySqlParser.ColumnDeclarationContext ctx) {
        LOGGER.debug("exitColumnDeclaration <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterConstraintDeclaration(MySqlParser.ConstraintDeclarationContext ctx) {
        LOGGER.debug("enterConstraintDeclaration <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitConstraintDeclaration(MySqlParser.ConstraintDeclarationContext ctx) {
        LOGGER.debug("exitConstraintDeclaration <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterIndexDeclaration(MySqlParser.IndexDeclarationContext ctx) {
        LOGGER.debug("enterIndexDeclaration <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitIndexDeclaration(MySqlParser.IndexDeclarationContext ctx) {
        LOGGER.debug("exitIndexDeclaration <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterColumnDefinition(MySqlParser.ColumnDefinitionContext ctx) {
        LOGGER.debug("enterColumnDefinition <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitColumnDefinition(MySqlParser.ColumnDefinitionContext ctx) {
        LOGGER.debug("exitColumnDefinition <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterNullColumnConstraint(MySqlParser.NullColumnConstraintContext ctx) {
        LOGGER.debug("enterNullColumnConstraint <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitNullColumnConstraint(MySqlParser.NullColumnConstraintContext ctx) {
        LOGGER.debug("exitNullColumnConstraint <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterDefaultColumnConstraint(MySqlParser.DefaultColumnConstraintContext ctx) {
        LOGGER.debug("enterDefaultColumnConstraint <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitDefaultColumnConstraint(MySqlParser.DefaultColumnConstraintContext ctx) {
        LOGGER.debug("exitDefaultColumnConstraint <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterAutoIncrementColumnConstraint(MySqlParser.AutoIncrementColumnConstraintContext ctx) {
        LOGGER.debug("enterAutoIncrementColumnConstraint <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitAutoIncrementColumnConstraint(MySqlParser.AutoIncrementColumnConstraintContext ctx) {
        LOGGER.debug("exitAutoIncrementColumnConstraint <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterPrimaryKeyColumnConstraint(MySqlParser.PrimaryKeyColumnConstraintContext ctx) {
        LOGGER.debug("enterPrimaryKeyColumnConstraint <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitPrimaryKeyColumnConstraint(MySqlParser.PrimaryKeyColumnConstraintContext ctx) {
        LOGGER.debug("exitPrimaryKeyColumnConstraint <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterUniqueKeyColumnConstraint(MySqlParser.UniqueKeyColumnConstraintContext ctx) {
        LOGGER.debug("enterUniqueKeyColumnConstraint <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitUniqueKeyColumnConstraint(MySqlParser.UniqueKeyColumnConstraintContext ctx) {
        LOGGER.debug("exitUniqueKeyColumnConstraint <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterCommentColumnConstraint(MySqlParser.CommentColumnConstraintContext ctx) {
        LOGGER.debug("enterCommentColumnConstraint <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitCommentColumnConstraint(MySqlParser.CommentColumnConstraintContext ctx) {
        LOGGER.debug("exitCommentColumnConstraint <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterFormatColumnConstraint(MySqlParser.FormatColumnConstraintContext ctx) {
        LOGGER.debug("enterFormatColumnConstraint <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitFormatColumnConstraint(MySqlParser.FormatColumnConstraintContext ctx) {
        LOGGER.debug("exitFormatColumnConstraint <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterStorageColumnConstraint(MySqlParser.StorageColumnConstraintContext ctx) {
        LOGGER.debug("enterStorageColumnConstraint <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitStorageColumnConstraint(MySqlParser.StorageColumnConstraintContext ctx) {
        LOGGER.debug("exitStorageColumnConstraint <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterReferenceColumnConstraint(MySqlParser.ReferenceColumnConstraintContext ctx) {
        LOGGER.debug("enterReferenceColumnConstraint <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitReferenceColumnConstraint(MySqlParser.ReferenceColumnConstraintContext ctx) {
        LOGGER.debug("exitReferenceColumnConstraint <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterCollateColumnConstraint(MySqlParser.CollateColumnConstraintContext ctx) {
        LOGGER.debug("enterCollateColumnConstraint <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitCollateColumnConstraint(MySqlParser.CollateColumnConstraintContext ctx) {
        LOGGER.debug("exitCollateColumnConstraint <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterGeneratedColumnConstraint(MySqlParser.GeneratedColumnConstraintContext ctx) {
        LOGGER.debug("enterGeneratedColumnConstraint <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitGeneratedColumnConstraint(MySqlParser.GeneratedColumnConstraintContext ctx) {
        LOGGER.debug("exitGeneratedColumnConstraint <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterSerialDefaultColumnConstraint(MySqlParser.SerialDefaultColumnConstraintContext ctx) {
        LOGGER.debug("enterSerialDefaultColumnConstraint <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitSerialDefaultColumnConstraint(MySqlParser.SerialDefaultColumnConstraintContext ctx) {
        LOGGER.debug("exitSerialDefaultColumnConstraint <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterCheckColumnConstraint(MySqlParser.CheckColumnConstraintContext ctx) {
        LOGGER.debug("enterCheckColumnConstraint <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitCheckColumnConstraint(MySqlParser.CheckColumnConstraintContext ctx) {
        LOGGER.debug("exitCheckColumnConstraint <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterPrimaryKeyTableConstraint(MySqlParser.PrimaryKeyTableConstraintContext ctx) {
        LOGGER.debug("enterPrimaryKeyTableConstraint <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitPrimaryKeyTableConstraint(MySqlParser.PrimaryKeyTableConstraintContext ctx) {
        LOGGER.debug("exitPrimaryKeyTableConstraint <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterUniqueKeyTableConstraint(MySqlParser.UniqueKeyTableConstraintContext ctx) {
        LOGGER.debug("enterUniqueKeyTableConstraint <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitUniqueKeyTableConstraint(MySqlParser.UniqueKeyTableConstraintContext ctx) {
        LOGGER.debug("exitUniqueKeyTableConstraint <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterForeignKeyTableConstraint(MySqlParser.ForeignKeyTableConstraintContext ctx) {
        LOGGER.debug("enterForeignKeyTableConstraint <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitForeignKeyTableConstraint(MySqlParser.ForeignKeyTableConstraintContext ctx) {
        LOGGER.debug("exitForeignKeyTableConstraint <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterCheckTableConstraint(MySqlParser.CheckTableConstraintContext ctx) {
        LOGGER.debug("enterCheckTableConstraint <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitCheckTableConstraint(MySqlParser.CheckTableConstraintContext ctx) {
        LOGGER.debug("exitCheckTableConstraint <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterReferenceDefinition(MySqlParser.ReferenceDefinitionContext ctx) {
        LOGGER.debug("enterReferenceDefinition <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitReferenceDefinition(MySqlParser.ReferenceDefinitionContext ctx) {
        LOGGER.debug("exitReferenceDefinition <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterReferenceAction(MySqlParser.ReferenceActionContext ctx) {
        LOGGER.debug("enterReferenceAction <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitReferenceAction(MySqlParser.ReferenceActionContext ctx) {
        LOGGER.debug("exitReferenceAction <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterReferenceControlType(MySqlParser.ReferenceControlTypeContext ctx) {
        LOGGER.debug("enterReferenceControlType <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitReferenceControlType(MySqlParser.ReferenceControlTypeContext ctx) {
        LOGGER.debug("exitReferenceControlType <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterSimpleIndexDeclaration(MySqlParser.SimpleIndexDeclarationContext ctx) {
        LOGGER.debug("enterSimpleIndexDeclaration <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitSimpleIndexDeclaration(MySqlParser.SimpleIndexDeclarationContext ctx) {
        LOGGER.debug("exitSimpleIndexDeclaration <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterSpecialIndexDeclaration(MySqlParser.SpecialIndexDeclarationContext ctx) {
        LOGGER.debug("enterSpecialIndexDeclaration <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitSpecialIndexDeclaration(MySqlParser.SpecialIndexDeclarationContext ctx) {
        LOGGER.debug("exitSpecialIndexDeclaration <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterTableOptionEngine(MySqlParser.TableOptionEngineContext ctx) {
        LOGGER.debug("enterTableOptionEngine <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitTableOptionEngine(MySqlParser.TableOptionEngineContext ctx) {
        LOGGER.debug("exitTableOptionEngine <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterTableOptionAutoIncrement(MySqlParser.TableOptionAutoIncrementContext ctx) {
        LOGGER.debug("enterTableOptionAutoIncrement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitTableOptionAutoIncrement(MySqlParser.TableOptionAutoIncrementContext ctx) {
        LOGGER.debug("exitTableOptionAutoIncrement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterTableOptionAverage(MySqlParser.TableOptionAverageContext ctx) {
        LOGGER.debug("enterTableOptionAverage <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitTableOptionAverage(MySqlParser.TableOptionAverageContext ctx) {
        LOGGER.debug("exitTableOptionAverage <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterTableOptionCharset(MySqlParser.TableOptionCharsetContext ctx) {
        LOGGER.debug("enterTableOptionCharset <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitTableOptionCharset(MySqlParser.TableOptionCharsetContext ctx) {
        LOGGER.debug("exitTableOptionCharset <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterTableOptionChecksum(MySqlParser.TableOptionChecksumContext ctx) {
        LOGGER.debug("enterTableOptionChecksum <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitTableOptionChecksum(MySqlParser.TableOptionChecksumContext ctx) {
        LOGGER.debug("exitTableOptionChecksum <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterTableOptionCollate(MySqlParser.TableOptionCollateContext ctx) {
        LOGGER.debug("enterTableOptionCollate <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitTableOptionCollate(MySqlParser.TableOptionCollateContext ctx) {
        LOGGER.debug("exitTableOptionCollate <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterTableOptionComment(MySqlParser.TableOptionCommentContext ctx) {
        LOGGER.debug("enterTableOptionComment <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitTableOptionComment(MySqlParser.TableOptionCommentContext ctx) {
        LOGGER.debug("exitTableOptionComment <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterTableOptionCompression(MySqlParser.TableOptionCompressionContext ctx) {
        LOGGER.debug("enterTableOptionCompression <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitTableOptionCompression(MySqlParser.TableOptionCompressionContext ctx) {
        LOGGER.debug("exitTableOptionCompression <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterTableOptionConnection(MySqlParser.TableOptionConnectionContext ctx) {
        LOGGER.debug("enterTableOptionConnection <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitTableOptionConnection(MySqlParser.TableOptionConnectionContext ctx) {
        LOGGER.debug("exitTableOptionConnection <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterTableOptionDataDirectory(MySqlParser.TableOptionDataDirectoryContext ctx) {
        LOGGER.debug("enterTableOptionDataDirectory <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitTableOptionDataDirectory(MySqlParser.TableOptionDataDirectoryContext ctx) {
        LOGGER.debug("exitTableOptionDataDirectory <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterTableOptionDelay(MySqlParser.TableOptionDelayContext ctx) {
        LOGGER.debug("enterTableOptionDelay <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitTableOptionDelay(MySqlParser.TableOptionDelayContext ctx) {
        LOGGER.debug("exitTableOptionDelay <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterTableOptionEncryption(MySqlParser.TableOptionEncryptionContext ctx) {
        LOGGER.debug("enterTableOptionEncryption <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitTableOptionEncryption(MySqlParser.TableOptionEncryptionContext ctx) {
        LOGGER.debug("exitTableOptionEncryption <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterTableOptionIndexDirectory(MySqlParser.TableOptionIndexDirectoryContext ctx) {
        LOGGER.debug("enterTableOptionIndexDirectory <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitTableOptionIndexDirectory(MySqlParser.TableOptionIndexDirectoryContext ctx) {
        LOGGER.debug("exitTableOptionIndexDirectory <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterTableOptionInsertMethod(MySqlParser.TableOptionInsertMethodContext ctx) {
        LOGGER.debug("enterTableOptionInsertMethod <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitTableOptionInsertMethod(MySqlParser.TableOptionInsertMethodContext ctx) {
        LOGGER.debug("exitTableOptionInsertMethod <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterTableOptionKeyBlockSize(MySqlParser.TableOptionKeyBlockSizeContext ctx) {
        LOGGER.debug("enterTableOptionKeyBlockSize <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitTableOptionKeyBlockSize(MySqlParser.TableOptionKeyBlockSizeContext ctx) {
        LOGGER.debug("exitTableOptionKeyBlockSize <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterTableOptionMaxRows(MySqlParser.TableOptionMaxRowsContext ctx) {
        LOGGER.debug("enterTableOptionMaxRows <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitTableOptionMaxRows(MySqlParser.TableOptionMaxRowsContext ctx) {
        LOGGER.debug("exitTableOptionMaxRows <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterTableOptionMinRows(MySqlParser.TableOptionMinRowsContext ctx) {
        LOGGER.debug("enterTableOptionMinRows <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitTableOptionMinRows(MySqlParser.TableOptionMinRowsContext ctx) {
        LOGGER.debug("exitTableOptionMinRows <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterTableOptionPackKeys(MySqlParser.TableOptionPackKeysContext ctx) {
        LOGGER.debug("enterTableOptionPackKeys <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitTableOptionPackKeys(MySqlParser.TableOptionPackKeysContext ctx) {
        LOGGER.debug("exitTableOptionPackKeys <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterTableOptionPassword(MySqlParser.TableOptionPasswordContext ctx) {
        LOGGER.debug("enterTableOptionPassword <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitTableOptionPassword(MySqlParser.TableOptionPasswordContext ctx) {
        LOGGER.debug("exitTableOptionPassword <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterTableOptionRowFormat(MySqlParser.TableOptionRowFormatContext ctx) {
        LOGGER.debug("enterTableOptionRowFormat <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitTableOptionRowFormat(MySqlParser.TableOptionRowFormatContext ctx) {
        LOGGER.debug("exitTableOptionRowFormat <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterTableOptionRecalculation(MySqlParser.TableOptionRecalculationContext ctx) {
        LOGGER.debug("enterTableOptionRecalculation <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitTableOptionRecalculation(MySqlParser.TableOptionRecalculationContext ctx) {
        LOGGER.debug("exitTableOptionRecalculation <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterTableOptionPersistent(MySqlParser.TableOptionPersistentContext ctx) {
        LOGGER.debug("enterTableOptionPersistent <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitTableOptionPersistent(MySqlParser.TableOptionPersistentContext ctx) {
        LOGGER.debug("exitTableOptionPersistent <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterTableOptionSamplePage(MySqlParser.TableOptionSamplePageContext ctx) {
        LOGGER.debug("enterTableOptionSamplePage <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitTableOptionSamplePage(MySqlParser.TableOptionSamplePageContext ctx) {
        LOGGER.debug("exitTableOptionSamplePage <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterTableOptionTablespace(MySqlParser.TableOptionTablespaceContext ctx) {
        LOGGER.debug("enterTableOptionTablespace <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitTableOptionTablespace(MySqlParser.TableOptionTablespaceContext ctx) {
        LOGGER.debug("exitTableOptionTablespace <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterTableOptionUnion(MySqlParser.TableOptionUnionContext ctx) {
        LOGGER.debug("enterTableOptionUnion <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitTableOptionUnion(MySqlParser.TableOptionUnionContext ctx) {
        LOGGER.debug("exitTableOptionUnion <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterTablespaceStorage(MySqlParser.TablespaceStorageContext ctx) {
        LOGGER.debug("enterTablespaceStorage <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitTablespaceStorage(MySqlParser.TablespaceStorageContext ctx) {
        LOGGER.debug("exitTablespaceStorage <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterPartitionDefinitions(MySqlParser.PartitionDefinitionsContext ctx) {
        LOGGER.debug("enterPartitionDefinitions <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitPartitionDefinitions(MySqlParser.PartitionDefinitionsContext ctx) {
        LOGGER.debug("exitPartitionDefinitions <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterPartitionFunctionHash(MySqlParser.PartitionFunctionHashContext ctx) {
        LOGGER.debug("enterPartitionFunctionHash <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitPartitionFunctionHash(MySqlParser.PartitionFunctionHashContext ctx) {
        LOGGER.debug("exitPartitionFunctionHash <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterPartitionFunctionKey(MySqlParser.PartitionFunctionKeyContext ctx) {
        LOGGER.debug("enterPartitionFunctionKey <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitPartitionFunctionKey(MySqlParser.PartitionFunctionKeyContext ctx) {
        LOGGER.debug("exitPartitionFunctionKey <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterPartitionFunctionRange(MySqlParser.PartitionFunctionRangeContext ctx) {
        LOGGER.debug("enterPartitionFunctionRange <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitPartitionFunctionRange(MySqlParser.PartitionFunctionRangeContext ctx) {
        LOGGER.debug("exitPartitionFunctionRange <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterPartitionFunctionList(MySqlParser.PartitionFunctionListContext ctx) {
        LOGGER.debug("enterPartitionFunctionList <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitPartitionFunctionList(MySqlParser.PartitionFunctionListContext ctx) {
        LOGGER.debug("exitPartitionFunctionList <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterSubPartitionFunctionHash(MySqlParser.SubPartitionFunctionHashContext ctx) {
        LOGGER.debug("enterSubPartitionFunctionHash <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitSubPartitionFunctionHash(MySqlParser.SubPartitionFunctionHashContext ctx) {
        LOGGER.debug("exitSubPartitionFunctionHash <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterSubPartitionFunctionKey(MySqlParser.SubPartitionFunctionKeyContext ctx) {
        LOGGER.debug("enterSubPartitionFunctionKey <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitSubPartitionFunctionKey(MySqlParser.SubPartitionFunctionKeyContext ctx) {
        LOGGER.debug("exitSubPartitionFunctionKey <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterPartitionComparision(MySqlParser.PartitionComparisionContext ctx) {
        LOGGER.debug("enterPartitionComparision <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitPartitionComparision(MySqlParser.PartitionComparisionContext ctx) {
        LOGGER.debug("exitPartitionComparision <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterPartitionListAtom(MySqlParser.PartitionListAtomContext ctx) {
        LOGGER.debug("enterPartitionListAtom <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitPartitionListAtom(MySqlParser.PartitionListAtomContext ctx) {
        LOGGER.debug("exitPartitionListAtom <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterPartitionListVector(MySqlParser.PartitionListVectorContext ctx) {
        LOGGER.debug("enterPartitionListVector <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitPartitionListVector(MySqlParser.PartitionListVectorContext ctx) {
        LOGGER.debug("exitPartitionListVector <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterPartitionSimple(MySqlParser.PartitionSimpleContext ctx) {
        LOGGER.debug("enterPartitionSimple <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitPartitionSimple(MySqlParser.PartitionSimpleContext ctx) {
        LOGGER.debug("exitPartitionSimple <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterPartitionDefinerAtom(MySqlParser.PartitionDefinerAtomContext ctx) {
        LOGGER.debug("enterPartitionDefinerAtom <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitPartitionDefinerAtom(MySqlParser.PartitionDefinerAtomContext ctx) {
        LOGGER.debug("exitPartitionDefinerAtom <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterPartitionDefinerVector(MySqlParser.PartitionDefinerVectorContext ctx) {
        LOGGER.debug("enterPartitionDefinerVector <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitPartitionDefinerVector(MySqlParser.PartitionDefinerVectorContext ctx) {
        LOGGER.debug("exitPartitionDefinerVector <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterSubpartitionDefinition(MySqlParser.SubpartitionDefinitionContext ctx) {
        LOGGER.debug("enterSubpartitionDefinition <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitSubpartitionDefinition(MySqlParser.SubpartitionDefinitionContext ctx) {
        LOGGER.debug("exitSubpartitionDefinition <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterPartitionOptionEngine(MySqlParser.PartitionOptionEngineContext ctx) {
        LOGGER.debug("enterPartitionOptionEngine <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitPartitionOptionEngine(MySqlParser.PartitionOptionEngineContext ctx) {
        LOGGER.debug("exitPartitionOptionEngine <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterPartitionOptionComment(MySqlParser.PartitionOptionCommentContext ctx) {
        LOGGER.debug("enterPartitionOptionComment <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitPartitionOptionComment(MySqlParser.PartitionOptionCommentContext ctx) {
        LOGGER.debug("exitPartitionOptionComment <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterPartitionOptionDataDirectory(MySqlParser.PartitionOptionDataDirectoryContext ctx) {
        LOGGER.debug("enterPartitionOptionDataDirectory <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitPartitionOptionDataDirectory(MySqlParser.PartitionOptionDataDirectoryContext ctx) {
        LOGGER.debug("exitPartitionOptionDataDirectory <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterPartitionOptionIndexDirectory(MySqlParser.PartitionOptionIndexDirectoryContext ctx) {
        LOGGER.debug("enterPartitionOptionIndexDirectory <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitPartitionOptionIndexDirectory(MySqlParser.PartitionOptionIndexDirectoryContext ctx) {
        LOGGER.debug("exitPartitionOptionIndexDirectory <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterPartitionOptionMaxRows(MySqlParser.PartitionOptionMaxRowsContext ctx) {
        LOGGER.debug("enterPartitionOptionMaxRows <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitPartitionOptionMaxRows(MySqlParser.PartitionOptionMaxRowsContext ctx) {
        LOGGER.debug("exitPartitionOptionMaxRows <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterPartitionOptionMinRows(MySqlParser.PartitionOptionMinRowsContext ctx) {
        LOGGER.debug("enterPartitionOptionMinRows <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitPartitionOptionMinRows(MySqlParser.PartitionOptionMinRowsContext ctx) {
        LOGGER.debug("exitPartitionOptionMinRows <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterPartitionOptionTablespace(MySqlParser.PartitionOptionTablespaceContext ctx) {
        LOGGER.debug("enterPartitionOptionTablespace <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitPartitionOptionTablespace(MySqlParser.PartitionOptionTablespaceContext ctx) {
        LOGGER.debug("exitPartitionOptionTablespace <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterPartitionOptionNodeGroup(MySqlParser.PartitionOptionNodeGroupContext ctx) {
        LOGGER.debug("enterPartitionOptionNodeGroup <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitPartitionOptionNodeGroup(MySqlParser.PartitionOptionNodeGroupContext ctx) {
        LOGGER.debug("exitPartitionOptionNodeGroup <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterAlterSimpleDatabase(MySqlParser.AlterSimpleDatabaseContext ctx) {
        LOGGER.debug("enterAlterSimpleDatabase <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitAlterSimpleDatabase(MySqlParser.AlterSimpleDatabaseContext ctx) {
        LOGGER.debug("exitAlterSimpleDatabase <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterAlterUpgradeName(MySqlParser.AlterUpgradeNameContext ctx) {
        LOGGER.debug("enterAlterUpgradeName <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitAlterUpgradeName(MySqlParser.AlterUpgradeNameContext ctx) {
        LOGGER.debug("exitAlterUpgradeName <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterAlterEvent(MySqlParser.AlterEventContext ctx) {
        LOGGER.debug("enterAlterEvent <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitAlterEvent(MySqlParser.AlterEventContext ctx) {
        LOGGER.debug("exitAlterEvent <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterAlterFunction(MySqlParser.AlterFunctionContext ctx) {
        LOGGER.debug("enterAlterFunction <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitAlterFunction(MySqlParser.AlterFunctionContext ctx) {
        LOGGER.debug("exitAlterFunction <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterAlterInstance(MySqlParser.AlterInstanceContext ctx) {
        LOGGER.debug("enterAlterInstance <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitAlterInstance(MySqlParser.AlterInstanceContext ctx) {
        LOGGER.debug("exitAlterInstance <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterAlterLogfileGroup(MySqlParser.AlterLogfileGroupContext ctx) {
        LOGGER.debug("enterAlterLogfileGroup <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitAlterLogfileGroup(MySqlParser.AlterLogfileGroupContext ctx) {
        LOGGER.debug("exitAlterLogfileGroup <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterAlterProcedure(MySqlParser.AlterProcedureContext ctx) {
        LOGGER.debug("enterAlterProcedure <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitAlterProcedure(MySqlParser.AlterProcedureContext ctx) {
        LOGGER.debug("exitAlterProcedure <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterAlterServer(MySqlParser.AlterServerContext ctx) {
        LOGGER.debug("enterAlterServer <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitAlterServer(MySqlParser.AlterServerContext ctx) {
        LOGGER.debug("exitAlterServer <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterAlterTable(MySqlParser.AlterTableContext ctx) {
        LOGGER.debug("enterAlterTable <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitAlterTable(MySqlParser.AlterTableContext ctx) {
        LOGGER.debug("exitAlterTable <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterAlterTablespace(MySqlParser.AlterTablespaceContext ctx) {
        LOGGER.debug("enterAlterTablespace <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitAlterTablespace(MySqlParser.AlterTablespaceContext ctx) {
        LOGGER.debug("exitAlterTablespace <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterAlterView(MySqlParser.AlterViewContext ctx) {
        LOGGER.debug("enterAlterView <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitAlterView(MySqlParser.AlterViewContext ctx) {
        LOGGER.debug("exitAlterView <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterAlterByTableOption(MySqlParser.AlterByTableOptionContext ctx) {
        LOGGER.debug("enterAlterByTableOption <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitAlterByTableOption(MySqlParser.AlterByTableOptionContext ctx) {
        LOGGER.debug("exitAlterByTableOption <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterAlterByAddColumn(MySqlParser.AlterByAddColumnContext ctx) {
        LOGGER.debug("enterAlterByAddColumn <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitAlterByAddColumn(MySqlParser.AlterByAddColumnContext ctx) {
        LOGGER.debug("exitAlterByAddColumn <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterAlterByAddColumns(MySqlParser.AlterByAddColumnsContext ctx) {
        LOGGER.debug("enterAlterByAddColumns <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitAlterByAddColumns(MySqlParser.AlterByAddColumnsContext ctx) {
        LOGGER.debug("exitAlterByAddColumns <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterAlterByAddIndex(MySqlParser.AlterByAddIndexContext ctx) {
        LOGGER.debug("enterAlterByAddIndex <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitAlterByAddIndex(MySqlParser.AlterByAddIndexContext ctx) {
        LOGGER.debug("exitAlterByAddIndex <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterAlterByAddPrimaryKey(MySqlParser.AlterByAddPrimaryKeyContext ctx) {
        LOGGER.debug("enterAlterByAddPrimaryKey <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitAlterByAddPrimaryKey(MySqlParser.AlterByAddPrimaryKeyContext ctx) {
        LOGGER.debug("exitAlterByAddPrimaryKey <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterAlterByAddUniqueKey(MySqlParser.AlterByAddUniqueKeyContext ctx) {
        LOGGER.debug("enterAlterByAddUniqueKey <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitAlterByAddUniqueKey(MySqlParser.AlterByAddUniqueKeyContext ctx) {
        LOGGER.debug("exitAlterByAddUniqueKey <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterAlterByAddSpecialIndex(MySqlParser.AlterByAddSpecialIndexContext ctx) {
        LOGGER.debug("enterAlterByAddSpecialIndex <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitAlterByAddSpecialIndex(MySqlParser.AlterByAddSpecialIndexContext ctx) {
        LOGGER.debug("exitAlterByAddSpecialIndex <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterAlterByAddForeignKey(MySqlParser.AlterByAddForeignKeyContext ctx) {
        LOGGER.debug("enterAlterByAddForeignKey <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitAlterByAddForeignKey(MySqlParser.AlterByAddForeignKeyContext ctx) {
        LOGGER.debug("exitAlterByAddForeignKey <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterAlterByAddCheckTableConstraint(MySqlParser.AlterByAddCheckTableConstraintContext ctx) {
        LOGGER.debug("enterAlterByAddCheckTableConstraint <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitAlterByAddCheckTableConstraint(MySqlParser.AlterByAddCheckTableConstraintContext ctx) {
        LOGGER.debug("exitAlterByAddCheckTableConstraint <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterAlterBySetAlgorithm(MySqlParser.AlterBySetAlgorithmContext ctx) {
        LOGGER.debug("enterAlterBySetAlgorithm <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitAlterBySetAlgorithm(MySqlParser.AlterBySetAlgorithmContext ctx) {
        LOGGER.debug("exitAlterBySetAlgorithm <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterAlterByChangeDefault(MySqlParser.AlterByChangeDefaultContext ctx) {
        LOGGER.debug("enterAlterByChangeDefault <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitAlterByChangeDefault(MySqlParser.AlterByChangeDefaultContext ctx) {
        LOGGER.debug("exitAlterByChangeDefault <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterAlterByChangeColumn(MySqlParser.AlterByChangeColumnContext ctx) {
        LOGGER.debug("enterAlterByChangeColumn <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitAlterByChangeColumn(MySqlParser.AlterByChangeColumnContext ctx) {
        LOGGER.debug("exitAlterByChangeColumn <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterAlterByRenameColumn(MySqlParser.AlterByRenameColumnContext ctx) {
        LOGGER.debug("enterAlterByRenameColumn <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitAlterByRenameColumn(MySqlParser.AlterByRenameColumnContext ctx) {
        LOGGER.debug("exitAlterByRenameColumn <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterAlterByLock(MySqlParser.AlterByLockContext ctx) {
        LOGGER.debug("enterAlterByLock <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitAlterByLock(MySqlParser.AlterByLockContext ctx) {
        LOGGER.debug("exitAlterByLock <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterAlterByModifyColumn(MySqlParser.AlterByModifyColumnContext ctx) {
        LOGGER.debug("enterAlterByModifyColumn <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitAlterByModifyColumn(MySqlParser.AlterByModifyColumnContext ctx) {
        LOGGER.debug("exitAlterByModifyColumn <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterAlterByDropColumn(MySqlParser.AlterByDropColumnContext ctx) {
        LOGGER.debug("enterAlterByDropColumn <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitAlterByDropColumn(MySqlParser.AlterByDropColumnContext ctx) {
        LOGGER.debug("exitAlterByDropColumn <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterAlterByDropConstraintCheck(MySqlParser.AlterByDropConstraintCheckContext ctx) {
        super.enterAlterByDropConstraintCheck(ctx);
    }

    @Override
    public void exitAlterByDropConstraintCheck(MySqlParser.AlterByDropConstraintCheckContext ctx) {
        super.exitAlterByDropConstraintCheck(ctx);
    }

    @Override
    public void enterAlterByDropPrimaryKey(MySqlParser.AlterByDropPrimaryKeyContext ctx) {
        LOGGER.debug("enterAlterByDropPrimaryKey <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitAlterByDropPrimaryKey(MySqlParser.AlterByDropPrimaryKeyContext ctx) {
        LOGGER.debug("exitAlterByDropPrimaryKey <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterAlterByRenameIndex(MySqlParser.AlterByRenameIndexContext ctx) {
        LOGGER.debug("enterAlterByRenameIndex <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitAlterByRenameIndex(MySqlParser.AlterByRenameIndexContext ctx) {
        LOGGER.debug("exitAlterByRenameIndex <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterAlterByAlterIndexVisibility(MySqlParser.AlterByAlterIndexVisibilityContext ctx) {
        LOGGER.debug("enterAlterByAlterIndexVisibility <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitAlterByAlterIndexVisibility(MySqlParser.AlterByAlterIndexVisibilityContext ctx) {
        LOGGER.debug("exitAlterByAlterIndexVisibility <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterAlterByDropIndex(MySqlParser.AlterByDropIndexContext ctx) {
        LOGGER.debug("enterAlterByDropIndex <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitAlterByDropIndex(MySqlParser.AlterByDropIndexContext ctx) {
        LOGGER.debug("exitAlterByDropIndex <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterAlterByDropForeignKey(MySqlParser.AlterByDropForeignKeyContext ctx) {
        LOGGER.debug("enterAlterByDropForeignKey <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitAlterByDropForeignKey(MySqlParser.AlterByDropForeignKeyContext ctx) {
        LOGGER.debug("exitAlterByDropForeignKey <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterAlterByDisableKeys(MySqlParser.AlterByDisableKeysContext ctx) {
        LOGGER.debug("enterAlterByDisableKeys <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitAlterByDisableKeys(MySqlParser.AlterByDisableKeysContext ctx) {
        LOGGER.debug("exitAlterByDisableKeys <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterAlterByEnableKeys(MySqlParser.AlterByEnableKeysContext ctx) {
        LOGGER.debug("enterAlterByEnableKeys <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitAlterByEnableKeys(MySqlParser.AlterByEnableKeysContext ctx) {
        LOGGER.debug("exitAlterByEnableKeys <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterAlterByRename(MySqlParser.AlterByRenameContext ctx) {
        LOGGER.debug("enterAlterByRename <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitAlterByRename(MySqlParser.AlterByRenameContext ctx) {
        LOGGER.debug("exitAlterByRename <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterAlterByOrder(MySqlParser.AlterByOrderContext ctx) {
        LOGGER.debug("enterAlterByOrder <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitAlterByOrder(MySqlParser.AlterByOrderContext ctx) {
        LOGGER.debug("exitAlterByOrder <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterAlterByConvertCharset(MySqlParser.AlterByConvertCharsetContext ctx) {
        LOGGER.debug("enterAlterByConvertCharset <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitAlterByConvertCharset(MySqlParser.AlterByConvertCharsetContext ctx) {
        LOGGER.debug("exitAlterByConvertCharset <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterAlterByDefaultCharset(MySqlParser.AlterByDefaultCharsetContext ctx) {
        LOGGER.debug("enterAlterByDefaultCharset <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitAlterByDefaultCharset(MySqlParser.AlterByDefaultCharsetContext ctx) {
        LOGGER.debug("exitAlterByDefaultCharset <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterAlterByDiscardTablespace(MySqlParser.AlterByDiscardTablespaceContext ctx) {
        LOGGER.debug("enterAlterByDiscardTablespace <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitAlterByDiscardTablespace(MySqlParser.AlterByDiscardTablespaceContext ctx) {
        LOGGER.debug("exitAlterByDiscardTablespace <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterAlterByImportTablespace(MySqlParser.AlterByImportTablespaceContext ctx) {
        LOGGER.debug("enterAlterByImportTablespace <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitAlterByImportTablespace(MySqlParser.AlterByImportTablespaceContext ctx) {
        LOGGER.debug("exitAlterByImportTablespace <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterAlterByForce(MySqlParser.AlterByForceContext ctx) {
        LOGGER.debug("enterAlterByForce <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitAlterByForce(MySqlParser.AlterByForceContext ctx) {
        LOGGER.debug("exitAlterByForce <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterAlterByValidate(MySqlParser.AlterByValidateContext ctx) {
        LOGGER.debug("enterAlterByValidate <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitAlterByValidate(MySqlParser.AlterByValidateContext ctx) {
        LOGGER.debug("exitAlterByValidate <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterAlterByAddPartition(MySqlParser.AlterByAddPartitionContext ctx) {
        LOGGER.debug("enterAlterByAddPartition <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitAlterByAddPartition(MySqlParser.AlterByAddPartitionContext ctx) {
        LOGGER.debug("exitAlterByAddPartition <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterAlterByDropPartition(MySqlParser.AlterByDropPartitionContext ctx) {
        LOGGER.debug("enterAlterByDropPartition <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitAlterByDropPartition(MySqlParser.AlterByDropPartitionContext ctx) {
        LOGGER.debug("exitAlterByDropPartition <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterAlterByDiscardPartition(MySqlParser.AlterByDiscardPartitionContext ctx) {
        LOGGER.debug("enterAlterByDiscardPartition <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitAlterByDiscardPartition(MySqlParser.AlterByDiscardPartitionContext ctx) {
        LOGGER.debug("exitAlterByDiscardPartition <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterAlterByImportPartition(MySqlParser.AlterByImportPartitionContext ctx) {
        LOGGER.debug("enterAlterByImportPartition <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitAlterByImportPartition(MySqlParser.AlterByImportPartitionContext ctx) {
        LOGGER.debug("exitAlterByImportPartition <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterAlterByTruncatePartition(MySqlParser.AlterByTruncatePartitionContext ctx) {
        LOGGER.debug("enterAlterByTruncatePartition <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitAlterByTruncatePartition(MySqlParser.AlterByTruncatePartitionContext ctx) {
        LOGGER.debug("exitAlterByTruncatePartition <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterAlterByCoalescePartition(MySqlParser.AlterByCoalescePartitionContext ctx) {
        LOGGER.debug("enterAlterByCoalescePartition <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitAlterByCoalescePartition(MySqlParser.AlterByCoalescePartitionContext ctx) {
        LOGGER.debug("exitAlterByCoalescePartition <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterAlterByReorganizePartition(MySqlParser.AlterByReorganizePartitionContext ctx) {
        LOGGER.debug("enterAlterByReorganizePartition <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitAlterByReorganizePartition(MySqlParser.AlterByReorganizePartitionContext ctx) {
        LOGGER.debug("exitAlterByReorganizePartition <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterAlterByExchangePartition(MySqlParser.AlterByExchangePartitionContext ctx) {
        LOGGER.debug("enterAlterByExchangePartition <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitAlterByExchangePartition(MySqlParser.AlterByExchangePartitionContext ctx) {
        LOGGER.debug("exitAlterByExchangePartition <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterAlterByAnalyzePartition(MySqlParser.AlterByAnalyzePartitionContext ctx) {
        LOGGER.debug("enterAlterByAnalyzePartition <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitAlterByAnalyzePartition(MySqlParser.AlterByAnalyzePartitionContext ctx) {
        LOGGER.debug("exitAlterByAnalyzePartition <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterAlterByCheckPartition(MySqlParser.AlterByCheckPartitionContext ctx) {
        LOGGER.debug("enterAlterByCheckPartition <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitAlterByCheckPartition(MySqlParser.AlterByCheckPartitionContext ctx) {
        LOGGER.debug("exitAlterByCheckPartition <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterAlterByOptimizePartition(MySqlParser.AlterByOptimizePartitionContext ctx) {
        LOGGER.debug("enterAlterByOptimizePartition <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitAlterByOptimizePartition(MySqlParser.AlterByOptimizePartitionContext ctx) {
        LOGGER.debug("exitAlterByOptimizePartition <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterAlterByRebuildPartition(MySqlParser.AlterByRebuildPartitionContext ctx) {
        LOGGER.debug("enterAlterByRebuildPartition <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitAlterByRebuildPartition(MySqlParser.AlterByRebuildPartitionContext ctx) {
        LOGGER.debug("exitAlterByRebuildPartition <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterAlterByRepairPartition(MySqlParser.AlterByRepairPartitionContext ctx) {
        LOGGER.debug("enterAlterByRepairPartition <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitAlterByRepairPartition(MySqlParser.AlterByRepairPartitionContext ctx) {
        LOGGER.debug("exitAlterByRepairPartition <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterAlterByRemovePartitioning(MySqlParser.AlterByRemovePartitioningContext ctx) {
        LOGGER.debug("enterAlterByRemovePartitioning <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitAlterByRemovePartitioning(MySqlParser.AlterByRemovePartitioningContext ctx) {
        LOGGER.debug("exitAlterByRemovePartitioning <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterAlterByUpgradePartitioning(MySqlParser.AlterByUpgradePartitioningContext ctx) {
        LOGGER.debug("enterAlterByUpgradePartitioning <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitAlterByUpgradePartitioning(MySqlParser.AlterByUpgradePartitioningContext ctx) {
        LOGGER.debug("exitAlterByUpgradePartitioning <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterDropDatabase(MySqlParser.DropDatabaseContext ctx) {
        LOGGER.debug("enterDropDatabase <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitDropDatabase(MySqlParser.DropDatabaseContext ctx) {
        LOGGER.debug("exitDropDatabase <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterDropEvent(MySqlParser.DropEventContext ctx) {
        LOGGER.debug("enterDropEvent <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitDropEvent(MySqlParser.DropEventContext ctx) {
        LOGGER.debug("exitDropEvent <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterDropIndex(MySqlParser.DropIndexContext ctx) {
        LOGGER.debug("enterDropIndex <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitDropIndex(MySqlParser.DropIndexContext ctx) {
        LOGGER.debug("exitDropIndex <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterDropLogfileGroup(MySqlParser.DropLogfileGroupContext ctx) {
        LOGGER.debug("enterDropLogfileGroup <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitDropLogfileGroup(MySqlParser.DropLogfileGroupContext ctx) {
        LOGGER.debug("exitDropLogfileGroup <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterDropProcedure(MySqlParser.DropProcedureContext ctx) {
        LOGGER.debug("enterDropProcedure <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitDropProcedure(MySqlParser.DropProcedureContext ctx) {
        LOGGER.debug("exitDropProcedure <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterDropFunction(MySqlParser.DropFunctionContext ctx) {
        LOGGER.debug("enterDropFunction <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitDropFunction(MySqlParser.DropFunctionContext ctx) {
        LOGGER.debug("exitDropFunction <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterDropServer(MySqlParser.DropServerContext ctx) {
        LOGGER.debug("enterDropServer <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitDropServer(MySqlParser.DropServerContext ctx) {
        LOGGER.debug("exitDropServer <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterDropTable(MySqlParser.DropTableContext ctx) {
        LOGGER.debug("enterDropTable <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitDropTable(MySqlParser.DropTableContext ctx) {
        LOGGER.debug("exitDropTable <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterDropTablespace(MySqlParser.DropTablespaceContext ctx) {
        LOGGER.debug("enterDropTablespace <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitDropTablespace(MySqlParser.DropTablespaceContext ctx) {
        LOGGER.debug("exitDropTablespace <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterDropTrigger(MySqlParser.DropTriggerContext ctx) {
        LOGGER.debug("enterDropTrigger <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitDropTrigger(MySqlParser.DropTriggerContext ctx) {
        LOGGER.debug("exitDropTrigger <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterDropView(MySqlParser.DropViewContext ctx) {
        LOGGER.debug("enterDropView <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitDropView(MySqlParser.DropViewContext ctx) {
        LOGGER.debug("exitDropView <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterRenameTable(MySqlParser.RenameTableContext ctx) {
        LOGGER.debug("enterRenameTable <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitRenameTable(MySqlParser.RenameTableContext ctx) {
        LOGGER.debug("exitRenameTable <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterRenameTableClause(MySqlParser.RenameTableClauseContext ctx) {
        LOGGER.debug("enterRenameTableClause <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitRenameTableClause(MySqlParser.RenameTableClauseContext ctx) {
        LOGGER.debug("exitRenameTableClause <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterTruncateTable(MySqlParser.TruncateTableContext ctx) {
        LOGGER.debug("enterTruncateTable <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitTruncateTable(MySqlParser.TruncateTableContext ctx) {
        LOGGER.debug("exitTruncateTable <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterCallStatement(MySqlParser.CallStatementContext ctx) {
        LOGGER.debug("enterCallStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitCallStatement(MySqlParser.CallStatementContext ctx) {
        LOGGER.debug("exitCallStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterDeleteStatement(MySqlParser.DeleteStatementContext ctx) {
        LOGGER.debug("enterDeleteStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitDeleteStatement(MySqlParser.DeleteStatementContext ctx) {
        LOGGER.debug("exitDeleteStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterDoStatement(MySqlParser.DoStatementContext ctx) {
        LOGGER.debug("enterDoStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitDoStatement(MySqlParser.DoStatementContext ctx) {
        LOGGER.debug("exitDoStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterHandlerStatement(MySqlParser.HandlerStatementContext ctx) {
        LOGGER.debug("enterHandlerStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitHandlerStatement(MySqlParser.HandlerStatementContext ctx) {
        LOGGER.debug("exitHandlerStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterInsertStatement(MySqlParser.InsertStatementContext ctx) {
        LOGGER.debug("enterInsertStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitInsertStatement(MySqlParser.InsertStatementContext ctx) {
        LOGGER.debug("exitInsertStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterLoadDataStatement(MySqlParser.LoadDataStatementContext ctx) {
        LOGGER.debug("enterLoadDataStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitLoadDataStatement(MySqlParser.LoadDataStatementContext ctx) {
        LOGGER.debug("exitLoadDataStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterLoadXmlStatement(MySqlParser.LoadXmlStatementContext ctx) {
        LOGGER.debug("enterLoadXmlStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitLoadXmlStatement(MySqlParser.LoadXmlStatementContext ctx) {
        LOGGER.debug("exitLoadXmlStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterReplaceStatement(MySqlParser.ReplaceStatementContext ctx) {
        LOGGER.debug("enterReplaceStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitReplaceStatement(MySqlParser.ReplaceStatementContext ctx) {
        LOGGER.debug("exitReplaceStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterSimpleSelect(MySqlParser.SimpleSelectContext ctx) {
        LOGGER.debug("enterSimpleSelect <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitSimpleSelect(MySqlParser.SimpleSelectContext ctx) {
        LOGGER.debug("exitSimpleSelect <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterParenthesisSelect(MySqlParser.ParenthesisSelectContext ctx) {
        LOGGER.debug("enterParenthesisSelect <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitParenthesisSelect(MySqlParser.ParenthesisSelectContext ctx) {
        LOGGER.debug("exitParenthesisSelect <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterUnionSelect(MySqlParser.UnionSelectContext ctx) {
        LOGGER.debug("enterUnionSelect <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitUnionSelect(MySqlParser.UnionSelectContext ctx) {
        LOGGER.debug("exitUnionSelect <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterUnionParenthesisSelect(MySqlParser.UnionParenthesisSelectContext ctx) {
        LOGGER.debug("enterUnionParenthesisSelect <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitUnionParenthesisSelect(MySqlParser.UnionParenthesisSelectContext ctx) {
        LOGGER.debug("exitUnionParenthesisSelect <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterUpdateStatement(MySqlParser.UpdateStatementContext ctx) {
        LOGGER.debug("enterUpdateStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitUpdateStatement(MySqlParser.UpdateStatementContext ctx) {
        LOGGER.debug("exitUpdateStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterInsertStatementValue(MySqlParser.InsertStatementValueContext ctx) {
        LOGGER.debug("enterInsertStatementValue <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitInsertStatementValue(MySqlParser.InsertStatementValueContext ctx) {
        LOGGER.debug("exitInsertStatementValue <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterUpdatedElement(MySqlParser.UpdatedElementContext ctx) {
        LOGGER.debug("enterUpdatedElement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitUpdatedElement(MySqlParser.UpdatedElementContext ctx) {
        LOGGER.debug("exitUpdatedElement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterAssignmentField(MySqlParser.AssignmentFieldContext ctx) {
        LOGGER.debug("enterAssignmentField <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitAssignmentField(MySqlParser.AssignmentFieldContext ctx) {
        LOGGER.debug("exitAssignmentField <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterLockClause(MySqlParser.LockClauseContext ctx) {
        LOGGER.debug("enterLockClause <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitLockClause(MySqlParser.LockClauseContext ctx) {
        LOGGER.debug("exitLockClause <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterSingleDeleteStatement(MySqlParser.SingleDeleteStatementContext ctx) {
        LOGGER.debug("enterSingleDeleteStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitSingleDeleteStatement(MySqlParser.SingleDeleteStatementContext ctx) {
        LOGGER.debug("exitSingleDeleteStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterMultipleDeleteStatement(MySqlParser.MultipleDeleteStatementContext ctx) {
        LOGGER.debug("enterMultipleDeleteStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitMultipleDeleteStatement(MySqlParser.MultipleDeleteStatementContext ctx) {
        LOGGER.debug("exitMultipleDeleteStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterHandlerOpenStatement(MySqlParser.HandlerOpenStatementContext ctx) {
        LOGGER.debug("enterHandlerOpenStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitHandlerOpenStatement(MySqlParser.HandlerOpenStatementContext ctx) {
        LOGGER.debug("exitHandlerOpenStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterHandlerReadIndexStatement(MySqlParser.HandlerReadIndexStatementContext ctx) {
        LOGGER.debug("enterHandlerReadIndexStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitHandlerReadIndexStatement(MySqlParser.HandlerReadIndexStatementContext ctx) {
        LOGGER.debug("exitHandlerReadIndexStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterHandlerReadStatement(MySqlParser.HandlerReadStatementContext ctx) {
        LOGGER.debug("enterHandlerReadStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitHandlerReadStatement(MySqlParser.HandlerReadStatementContext ctx) {
        LOGGER.debug("exitHandlerReadStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterHandlerCloseStatement(MySqlParser.HandlerCloseStatementContext ctx) {
        LOGGER.debug("enterHandlerCloseStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitHandlerCloseStatement(MySqlParser.HandlerCloseStatementContext ctx) {
        LOGGER.debug("exitHandlerCloseStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterSingleUpdateStatement(MySqlParser.SingleUpdateStatementContext ctx) {
        LOGGER.debug("enterSingleUpdateStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitSingleUpdateStatement(MySqlParser.SingleUpdateStatementContext ctx) {
        LOGGER.debug("exitSingleUpdateStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterMultipleUpdateStatement(MySqlParser.MultipleUpdateStatementContext ctx) {
        LOGGER.debug("enterMultipleUpdateStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitMultipleUpdateStatement(MySqlParser.MultipleUpdateStatementContext ctx) {
        LOGGER.debug("exitMultipleUpdateStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterOrderByClause(MySqlParser.OrderByClauseContext ctx) {
        LOGGER.debug("enterOrderByClause <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitOrderByClause(MySqlParser.OrderByClauseContext ctx) {
        LOGGER.debug("exitOrderByClause <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterOrderByExpression(MySqlParser.OrderByExpressionContext ctx) {
        LOGGER.debug("enterOrderByExpression <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitOrderByExpression(MySqlParser.OrderByExpressionContext ctx) {
        LOGGER.debug("exitOrderByExpression <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterTableSources(MySqlParser.TableSourcesContext ctx) {
        LOGGER.debug("enterTableSources <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitTableSources(MySqlParser.TableSourcesContext ctx) {
        LOGGER.debug("exitTableSources <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterTableSourceBase(MySqlParser.TableSourceBaseContext ctx) {
        LOGGER.debug("enterTableSourceBase <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitTableSourceBase(MySqlParser.TableSourceBaseContext ctx) {
        LOGGER.debug("exitTableSourceBase <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterTableSourceNested(MySqlParser.TableSourceNestedContext ctx) {
        LOGGER.debug("enterTableSourceNested <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitTableSourceNested(MySqlParser.TableSourceNestedContext ctx) {
        LOGGER.debug("exitTableSourceNested <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterAtomTableItem(MySqlParser.AtomTableItemContext ctx) {
        LOGGER.debug("enterAtomTableItem <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitAtomTableItem(MySqlParser.AtomTableItemContext ctx) {
        LOGGER.debug("exitAtomTableItem <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterSubqueryTableItem(MySqlParser.SubqueryTableItemContext ctx) {
        LOGGER.debug("enterSubqueryTableItem <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitSubqueryTableItem(MySqlParser.SubqueryTableItemContext ctx) {
        LOGGER.debug("exitSubqueryTableItem <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterTableSourcesItem(MySqlParser.TableSourcesItemContext ctx) {
        LOGGER.debug("enterTableSourcesItem <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitTableSourcesItem(MySqlParser.TableSourcesItemContext ctx) {
        LOGGER.debug("exitTableSourcesItem <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterIndexHint(MySqlParser.IndexHintContext ctx) {
        LOGGER.debug("enterIndexHint <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitIndexHint(MySqlParser.IndexHintContext ctx) {
        LOGGER.debug("exitIndexHint <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterIndexHintType(MySqlParser.IndexHintTypeContext ctx) {
        LOGGER.debug("enterIndexHintType <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitIndexHintType(MySqlParser.IndexHintTypeContext ctx) {
        LOGGER.debug("exitIndexHintType <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterInnerJoin(MySqlParser.InnerJoinContext ctx) {
        LOGGER.debug("enterInnerJoin <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitInnerJoin(MySqlParser.InnerJoinContext ctx) {
        LOGGER.debug("exitInnerJoin <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterStraightJoin(MySqlParser.StraightJoinContext ctx) {
        LOGGER.debug("enterStraightJoin <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitStraightJoin(MySqlParser.StraightJoinContext ctx) {
        LOGGER.debug("exitStraightJoin <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterOuterJoin(MySqlParser.OuterJoinContext ctx) {
        LOGGER.debug("enterOuterJoin <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitOuterJoin(MySqlParser.OuterJoinContext ctx) {
        LOGGER.debug("exitOuterJoin <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterNaturalJoin(MySqlParser.NaturalJoinContext ctx) {
        LOGGER.debug("enterNaturalJoin <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitNaturalJoin(MySqlParser.NaturalJoinContext ctx) {
        LOGGER.debug("exitNaturalJoin <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterQueryExpression(MySqlParser.QueryExpressionContext ctx) {
        LOGGER.debug("enterQueryExpression <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitQueryExpression(MySqlParser.QueryExpressionContext ctx) {
        LOGGER.debug("exitQueryExpression <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterQueryExpressionNointo(MySqlParser.QueryExpressionNointoContext ctx) {
        LOGGER.debug("enterQueryExpressionNointo <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitQueryExpressionNointo(MySqlParser.QueryExpressionNointoContext ctx) {
        LOGGER.debug("exitQueryExpressionNointo <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterQuerySpecification(MySqlParser.QuerySpecificationContext ctx) {
        LOGGER.debug("enterQuerySpecification <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitQuerySpecification(MySqlParser.QuerySpecificationContext ctx) {
        LOGGER.debug("exitQuerySpecification <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterQuerySpecificationNointo(MySqlParser.QuerySpecificationNointoContext ctx) {
        LOGGER.debug("enterQuerySpecificationNointo <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitQuerySpecificationNointo(MySqlParser.QuerySpecificationNointoContext ctx) {
        LOGGER.debug("exitQuerySpecificationNointo <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterUnionParenthesis(MySqlParser.UnionParenthesisContext ctx) {
        LOGGER.debug("enterUnionParenthesis <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitUnionParenthesis(MySqlParser.UnionParenthesisContext ctx) {
        LOGGER.debug("exitUnionParenthesis <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterUnionStatement(MySqlParser.UnionStatementContext ctx) {
        LOGGER.debug("enterUnionStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitUnionStatement(MySqlParser.UnionStatementContext ctx) {
        LOGGER.debug("exitUnionStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterSelectSpec(MySqlParser.SelectSpecContext ctx) {
        LOGGER.debug("enterSelectSpec <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitSelectSpec(MySqlParser.SelectSpecContext ctx) {
        LOGGER.debug("exitSelectSpec <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterSelectElements(MySqlParser.SelectElementsContext ctx) {
        LOGGER.debug("enterSelectElements <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitSelectElements(MySqlParser.SelectElementsContext ctx) {
        LOGGER.debug("exitSelectElements <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterSelectStarElement(MySqlParser.SelectStarElementContext ctx) {
        LOGGER.debug("enterSelectStarElement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitSelectStarElement(MySqlParser.SelectStarElementContext ctx) {
        LOGGER.debug("exitSelectStarElement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterSelectColumnElement(MySqlParser.SelectColumnElementContext ctx) {
        LOGGER.debug("enterSelectColumnElement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitSelectColumnElement(MySqlParser.SelectColumnElementContext ctx) {
        LOGGER.debug("exitSelectColumnElement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterSelectFunctionElement(MySqlParser.SelectFunctionElementContext ctx) {
        LOGGER.debug("enterSelectFunctionElement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitSelectFunctionElement(MySqlParser.SelectFunctionElementContext ctx) {
        LOGGER.debug("exitSelectFunctionElement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterSelectExpressionElement(MySqlParser.SelectExpressionElementContext ctx) {
        LOGGER.debug("enterSelectExpressionElement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitSelectExpressionElement(MySqlParser.SelectExpressionElementContext ctx) {
        LOGGER.debug("exitSelectExpressionElement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterSelectIntoVariables(MySqlParser.SelectIntoVariablesContext ctx) {
        LOGGER.debug("enterSelectIntoVariables <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitSelectIntoVariables(MySqlParser.SelectIntoVariablesContext ctx) {
        LOGGER.debug("exitSelectIntoVariables <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterSelectIntoDumpFile(MySqlParser.SelectIntoDumpFileContext ctx) {
        LOGGER.debug("enterSelectIntoDumpFile <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitSelectIntoDumpFile(MySqlParser.SelectIntoDumpFileContext ctx) {
        LOGGER.debug("exitSelectIntoDumpFile <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterSelectIntoTextFile(MySqlParser.SelectIntoTextFileContext ctx) {
        LOGGER.debug("enterSelectIntoTextFile <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitSelectIntoTextFile(MySqlParser.SelectIntoTextFileContext ctx) {
        LOGGER.debug("exitSelectIntoTextFile <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterSelectFieldsInto(MySqlParser.SelectFieldsIntoContext ctx) {
        LOGGER.debug("enterSelectFieldsInto <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitSelectFieldsInto(MySqlParser.SelectFieldsIntoContext ctx) {
        LOGGER.debug("exitSelectFieldsInto <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterSelectLinesInto(MySqlParser.SelectLinesIntoContext ctx) {
        LOGGER.debug("enterSelectLinesInto <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitSelectLinesInto(MySqlParser.SelectLinesIntoContext ctx) {
        LOGGER.debug("exitSelectLinesInto <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterFromClause(MySqlParser.FromClauseContext ctx) {
        LOGGER.debug("enterFromClause <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitFromClause(MySqlParser.FromClauseContext ctx) {
        LOGGER.debug("exitFromClause <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterGroupByItem(MySqlParser.GroupByItemContext ctx) {
        LOGGER.debug("enterGroupByItem <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitGroupByItem(MySqlParser.GroupByItemContext ctx) {
        LOGGER.debug("exitGroupByItem <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterLimitClause(MySqlParser.LimitClauseContext ctx) {
        LOGGER.debug("enterLimitClause <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitLimitClause(MySqlParser.LimitClauseContext ctx) {
        LOGGER.debug("exitLimitClause <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterLimitClauseAtom(MySqlParser.LimitClauseAtomContext ctx) {
        LOGGER.debug("enterLimitClauseAtom <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitLimitClauseAtom(MySqlParser.LimitClauseAtomContext ctx) {
        LOGGER.debug("exitLimitClauseAtom <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterStartTransaction(MySqlParser.StartTransactionContext ctx) {
        LOGGER.debug("enterStartTransaction <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitStartTransaction(MySqlParser.StartTransactionContext ctx) {
        LOGGER.debug("exitStartTransaction <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterBeginWork(MySqlParser.BeginWorkContext ctx) {
        LOGGER.debug("enterBeginWork <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitBeginWork(MySqlParser.BeginWorkContext ctx) {
        LOGGER.debug("exitBeginWork <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterCommitWork(MySqlParser.CommitWorkContext ctx) {
        LOGGER.debug("enterCommitWork <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitCommitWork(MySqlParser.CommitWorkContext ctx) {
        LOGGER.debug("exitCommitWork <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterRollbackWork(MySqlParser.RollbackWorkContext ctx) {
        LOGGER.debug("enterRollbackWork <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitRollbackWork(MySqlParser.RollbackWorkContext ctx) {
        LOGGER.debug("exitRollbackWork <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterSavepointStatement(MySqlParser.SavepointStatementContext ctx) {
        LOGGER.debug("enterSavepointStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitSavepointStatement(MySqlParser.SavepointStatementContext ctx) {
        LOGGER.debug("exitSavepointStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterRollbackStatement(MySqlParser.RollbackStatementContext ctx) {
        LOGGER.debug("enterRollbackStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitRollbackStatement(MySqlParser.RollbackStatementContext ctx) {
        LOGGER.debug("exitRollbackStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterReleaseStatement(MySqlParser.ReleaseStatementContext ctx) {
        LOGGER.debug("enterReleaseStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitReleaseStatement(MySqlParser.ReleaseStatementContext ctx) {
        LOGGER.debug("exitReleaseStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterLockTables(MySqlParser.LockTablesContext ctx) {
        LOGGER.debug("enterLockTables <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitLockTables(MySqlParser.LockTablesContext ctx) {
        LOGGER.debug("exitLockTables <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterUnlockTables(MySqlParser.UnlockTablesContext ctx) {
        LOGGER.debug("enterUnlockTables <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitUnlockTables(MySqlParser.UnlockTablesContext ctx) {
        LOGGER.debug("exitUnlockTables <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterSetAutocommitStatement(MySqlParser.SetAutocommitStatementContext ctx) {
        LOGGER.debug("enterSetAutocommitStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitSetAutocommitStatement(MySqlParser.SetAutocommitStatementContext ctx) {
        LOGGER.debug("exitSetAutocommitStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterSetTransactionStatement(MySqlParser.SetTransactionStatementContext ctx) {
        LOGGER.debug("enterSetTransactionStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitSetTransactionStatement(MySqlParser.SetTransactionStatementContext ctx) {
        LOGGER.debug("exitSetTransactionStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterTransactionMode(MySqlParser.TransactionModeContext ctx) {
        LOGGER.debug("enterTransactionMode <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitTransactionMode(MySqlParser.TransactionModeContext ctx) {
        LOGGER.debug("exitTransactionMode <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterLockTableElement(MySqlParser.LockTableElementContext ctx) {
        LOGGER.debug("enterLockTableElement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitLockTableElement(MySqlParser.LockTableElementContext ctx) {
        LOGGER.debug("exitLockTableElement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterLockAction(MySqlParser.LockActionContext ctx) {
        LOGGER.debug("enterLockAction <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitLockAction(MySqlParser.LockActionContext ctx) {
        LOGGER.debug("exitLockAction <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterTransactionOption(MySqlParser.TransactionOptionContext ctx) {
        LOGGER.debug("enterTransactionOption <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitTransactionOption(MySqlParser.TransactionOptionContext ctx) {
        LOGGER.debug("exitTransactionOption <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterTransactionLevel(MySqlParser.TransactionLevelContext ctx) {
        LOGGER.debug("enterTransactionLevel <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitTransactionLevel(MySqlParser.TransactionLevelContext ctx) {
        LOGGER.debug("exitTransactionLevel <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterChangeMaster(MySqlParser.ChangeMasterContext ctx) {
        LOGGER.debug("enterChangeMaster <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitChangeMaster(MySqlParser.ChangeMasterContext ctx) {
        LOGGER.debug("exitChangeMaster <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterChangeReplicationFilter(MySqlParser.ChangeReplicationFilterContext ctx) {
        LOGGER.debug("enterChangeReplicationFilter <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitChangeReplicationFilter(MySqlParser.ChangeReplicationFilterContext ctx) {
        LOGGER.debug("exitChangeReplicationFilter <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterPurgeBinaryLogs(MySqlParser.PurgeBinaryLogsContext ctx) {
        LOGGER.debug("enterPurgeBinaryLogs <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitPurgeBinaryLogs(MySqlParser.PurgeBinaryLogsContext ctx) {
        LOGGER.debug("exitPurgeBinaryLogs <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterResetMaster(MySqlParser.ResetMasterContext ctx) {
        LOGGER.debug("enterResetMaster <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitResetMaster(MySqlParser.ResetMasterContext ctx) {
        LOGGER.debug("exitResetMaster <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterResetSlave(MySqlParser.ResetSlaveContext ctx) {
        LOGGER.debug("enterResetSlave <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitResetSlave(MySqlParser.ResetSlaveContext ctx) {
        LOGGER.debug("exitResetSlave <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterStartSlave(MySqlParser.StartSlaveContext ctx) {
        LOGGER.debug("enterStartSlave <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitStartSlave(MySqlParser.StartSlaveContext ctx) {
        LOGGER.debug("exitStartSlave <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterStopSlave(MySqlParser.StopSlaveContext ctx) {
        LOGGER.debug("enterStopSlave <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitStopSlave(MySqlParser.StopSlaveContext ctx) {
        LOGGER.debug("exitStopSlave <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterStartGroupReplication(MySqlParser.StartGroupReplicationContext ctx) {
        LOGGER.debug("enterStartGroupReplication <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitStartGroupReplication(MySqlParser.StartGroupReplicationContext ctx) {
        LOGGER.debug("exitStartGroupReplication <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterStopGroupReplication(MySqlParser.StopGroupReplicationContext ctx) {
        LOGGER.debug("enterStopGroupReplication <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitStopGroupReplication(MySqlParser.StopGroupReplicationContext ctx) {
        LOGGER.debug("exitStopGroupReplication <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterMasterStringOption(MySqlParser.MasterStringOptionContext ctx) {
        LOGGER.debug("enterMasterStringOption <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitMasterStringOption(MySqlParser.MasterStringOptionContext ctx) {
        LOGGER.debug("exitMasterStringOption <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterMasterDecimalOption(MySqlParser.MasterDecimalOptionContext ctx) {
        LOGGER.debug("enterMasterDecimalOption <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitMasterDecimalOption(MySqlParser.MasterDecimalOptionContext ctx) {
        LOGGER.debug("exitMasterDecimalOption <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterMasterBoolOption(MySqlParser.MasterBoolOptionContext ctx) {
        LOGGER.debug("enterMasterBoolOption <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitMasterBoolOption(MySqlParser.MasterBoolOptionContext ctx) {
        LOGGER.debug("exitMasterBoolOption <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterMasterRealOption(MySqlParser.MasterRealOptionContext ctx) {
        LOGGER.debug("enterMasterRealOption <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitMasterRealOption(MySqlParser.MasterRealOptionContext ctx) {
        LOGGER.debug("exitMasterRealOption <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterMasterUidListOption(MySqlParser.MasterUidListOptionContext ctx) {
        LOGGER.debug("enterMasterUidListOption <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitMasterUidListOption(MySqlParser.MasterUidListOptionContext ctx) {
        LOGGER.debug("exitMasterUidListOption <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterStringMasterOption(MySqlParser.StringMasterOptionContext ctx) {
        LOGGER.debug("enterStringMasterOption <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitStringMasterOption(MySqlParser.StringMasterOptionContext ctx) {
        LOGGER.debug("exitStringMasterOption <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterDecimalMasterOption(MySqlParser.DecimalMasterOptionContext ctx) {
        LOGGER.debug("enterDecimalMasterOption <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitDecimalMasterOption(MySqlParser.DecimalMasterOptionContext ctx) {
        LOGGER.debug("exitDecimalMasterOption <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterBoolMasterOption(MySqlParser.BoolMasterOptionContext ctx) {
        LOGGER.debug("enterBoolMasterOption <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitBoolMasterOption(MySqlParser.BoolMasterOptionContext ctx) {
        LOGGER.debug("exitBoolMasterOption <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterChannelOption(MySqlParser.ChannelOptionContext ctx) {
        LOGGER.debug("enterChannelOption <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitChannelOption(MySqlParser.ChannelOptionContext ctx) {
        LOGGER.debug("exitChannelOption <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterDoDbReplication(MySqlParser.DoDbReplicationContext ctx) {
        LOGGER.debug("enterDoDbReplication <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitDoDbReplication(MySqlParser.DoDbReplicationContext ctx) {
        LOGGER.debug("exitDoDbReplication <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterIgnoreDbReplication(MySqlParser.IgnoreDbReplicationContext ctx) {
        LOGGER.debug("enterIgnoreDbReplication <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitIgnoreDbReplication(MySqlParser.IgnoreDbReplicationContext ctx) {
        LOGGER.debug("exitIgnoreDbReplication <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterDoTableReplication(MySqlParser.DoTableReplicationContext ctx) {
        LOGGER.debug("enterDoTableReplication <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitDoTableReplication(MySqlParser.DoTableReplicationContext ctx) {
        LOGGER.debug("exitDoTableReplication <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterIgnoreTableReplication(MySqlParser.IgnoreTableReplicationContext ctx) {
        LOGGER.debug("enterIgnoreTableReplication <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitIgnoreTableReplication(MySqlParser.IgnoreTableReplicationContext ctx) {
        LOGGER.debug("exitIgnoreTableReplication <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterWildDoTableReplication(MySqlParser.WildDoTableReplicationContext ctx) {
        LOGGER.debug("enterWildDoTableReplication <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitWildDoTableReplication(MySqlParser.WildDoTableReplicationContext ctx) {
        LOGGER.debug("exitWildDoTableReplication <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterWildIgnoreTableReplication(MySqlParser.WildIgnoreTableReplicationContext ctx) {
        LOGGER.debug("enterWildIgnoreTableReplication <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitWildIgnoreTableReplication(MySqlParser.WildIgnoreTableReplicationContext ctx) {
        LOGGER.debug("exitWildIgnoreTableReplication <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterRewriteDbReplication(MySqlParser.RewriteDbReplicationContext ctx) {
        LOGGER.debug("enterRewriteDbReplication <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitRewriteDbReplication(MySqlParser.RewriteDbReplicationContext ctx) {
        LOGGER.debug("exitRewriteDbReplication <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterTablePair(MySqlParser.TablePairContext ctx) {
        LOGGER.debug("enterTablePair <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitTablePair(MySqlParser.TablePairContext ctx) {
        LOGGER.debug("exitTablePair <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterThreadType(MySqlParser.ThreadTypeContext ctx) {
        LOGGER.debug("enterThreadType <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitThreadType(MySqlParser.ThreadTypeContext ctx) {
        LOGGER.debug("exitThreadType <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterGtidsUntilOption(MySqlParser.GtidsUntilOptionContext ctx) {
        LOGGER.debug("enterGtidsUntilOption <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitGtidsUntilOption(MySqlParser.GtidsUntilOptionContext ctx) {
        LOGGER.debug("exitGtidsUntilOption <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterMasterLogUntilOption(MySqlParser.MasterLogUntilOptionContext ctx) {
        LOGGER.debug("enterMasterLogUntilOption <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitMasterLogUntilOption(MySqlParser.MasterLogUntilOptionContext ctx) {
        LOGGER.debug("exitMasterLogUntilOption <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterRelayLogUntilOption(MySqlParser.RelayLogUntilOptionContext ctx) {
        LOGGER.debug("enterRelayLogUntilOption <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitRelayLogUntilOption(MySqlParser.RelayLogUntilOptionContext ctx) {
        LOGGER.debug("exitRelayLogUntilOption <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterSqlGapsUntilOption(MySqlParser.SqlGapsUntilOptionContext ctx) {
        LOGGER.debug("enterSqlGapsUntilOption <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitSqlGapsUntilOption(MySqlParser.SqlGapsUntilOptionContext ctx) {
        LOGGER.debug("exitSqlGapsUntilOption <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterUserConnectionOption(MySqlParser.UserConnectionOptionContext ctx) {
        LOGGER.debug("enterUserConnectionOption <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitUserConnectionOption(MySqlParser.UserConnectionOptionContext ctx) {
        LOGGER.debug("exitUserConnectionOption <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterPasswordConnectionOption(MySqlParser.PasswordConnectionOptionContext ctx) {
        LOGGER.debug("enterPasswordConnectionOption <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitPasswordConnectionOption(MySqlParser.PasswordConnectionOptionContext ctx) {
        LOGGER.debug("exitPasswordConnectionOption <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterDefaultAuthConnectionOption(MySqlParser.DefaultAuthConnectionOptionContext ctx) {
        LOGGER.debug("enterDefaultAuthConnectionOption <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitDefaultAuthConnectionOption(MySqlParser.DefaultAuthConnectionOptionContext ctx) {
        LOGGER.debug("exitDefaultAuthConnectionOption <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterPluginDirConnectionOption(MySqlParser.PluginDirConnectionOptionContext ctx) {
        LOGGER.debug("enterPluginDirConnectionOption <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitPluginDirConnectionOption(MySqlParser.PluginDirConnectionOptionContext ctx) {
        LOGGER.debug("exitPluginDirConnectionOption <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterGtuidSet(MySqlParser.GtuidSetContext ctx) {
        LOGGER.debug("enterGtuidSet <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitGtuidSet(MySqlParser.GtuidSetContext ctx) {
        LOGGER.debug("exitGtuidSet <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterXaStartTransaction(MySqlParser.XaStartTransactionContext ctx) {
        LOGGER.debug("enterXaStartTransaction <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitXaStartTransaction(MySqlParser.XaStartTransactionContext ctx) {
        LOGGER.debug("exitXaStartTransaction <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterXaEndTransaction(MySqlParser.XaEndTransactionContext ctx) {
        LOGGER.debug("enterXaEndTransaction <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitXaEndTransaction(MySqlParser.XaEndTransactionContext ctx) {
        LOGGER.debug("exitXaEndTransaction <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterXaPrepareStatement(MySqlParser.XaPrepareStatementContext ctx) {
        LOGGER.debug("enterXaPrepareStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitXaPrepareStatement(MySqlParser.XaPrepareStatementContext ctx) {
        LOGGER.debug("exitXaPrepareStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterXaCommitWork(MySqlParser.XaCommitWorkContext ctx) {
        LOGGER.debug("enterXaCommitWork <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitXaCommitWork(MySqlParser.XaCommitWorkContext ctx) {
        LOGGER.debug("exitXaCommitWork <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterXaRollbackWork(MySqlParser.XaRollbackWorkContext ctx) {
        LOGGER.debug("enterXaRollbackWork <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitXaRollbackWork(MySqlParser.XaRollbackWorkContext ctx) {
        LOGGER.debug("exitXaRollbackWork <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterXaRecoverWork(MySqlParser.XaRecoverWorkContext ctx) {
        LOGGER.debug("enterXaRecoverWork <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitXaRecoverWork(MySqlParser.XaRecoverWorkContext ctx) {
        LOGGER.debug("exitXaRecoverWork <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterPrepareStatement(MySqlParser.PrepareStatementContext ctx) {
        LOGGER.debug("enterPrepareStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitPrepareStatement(MySqlParser.PrepareStatementContext ctx) {
        LOGGER.debug("exitPrepareStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterExecuteStatement(MySqlParser.ExecuteStatementContext ctx) {
        LOGGER.debug("enterExecuteStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitExecuteStatement(MySqlParser.ExecuteStatementContext ctx) {
        LOGGER.debug("exitExecuteStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterDeallocatePrepare(MySqlParser.DeallocatePrepareContext ctx) {
        LOGGER.debug("enterDeallocatePrepare <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitDeallocatePrepare(MySqlParser.DeallocatePrepareContext ctx) {
        LOGGER.debug("exitDeallocatePrepare <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterRoutineBody(MySqlParser.RoutineBodyContext ctx) {
        LOGGER.debug("enterRoutineBody <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitRoutineBody(MySqlParser.RoutineBodyContext ctx) {
        LOGGER.debug("exitRoutineBody <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterBlockStatement(MySqlParser.BlockStatementContext ctx) {
        LOGGER.debug("enterBlockStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitBlockStatement(MySqlParser.BlockStatementContext ctx) {
        LOGGER.debug("exitBlockStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterCaseStatement(MySqlParser.CaseStatementContext ctx) {
        LOGGER.debug("enterCaseStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitCaseStatement(MySqlParser.CaseStatementContext ctx) {
        LOGGER.debug("exitCaseStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterIfStatement(MySqlParser.IfStatementContext ctx) {
        LOGGER.debug("enterIfStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitIfStatement(MySqlParser.IfStatementContext ctx) {
        LOGGER.debug("exitIfStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterIterateStatement(MySqlParser.IterateStatementContext ctx) {
        LOGGER.debug("enterIterateStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitIterateStatement(MySqlParser.IterateStatementContext ctx) {
        LOGGER.debug("exitIterateStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterLeaveStatement(MySqlParser.LeaveStatementContext ctx) {
        LOGGER.debug("enterLeaveStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitLeaveStatement(MySqlParser.LeaveStatementContext ctx) {
        LOGGER.debug("exitLeaveStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterLoopStatement(MySqlParser.LoopStatementContext ctx) {
        LOGGER.debug("enterLoopStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitLoopStatement(MySqlParser.LoopStatementContext ctx) {
        LOGGER.debug("exitLoopStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterRepeatStatement(MySqlParser.RepeatStatementContext ctx) {
        LOGGER.debug("enterRepeatStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitRepeatStatement(MySqlParser.RepeatStatementContext ctx) {
        LOGGER.debug("exitRepeatStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterReturnStatement(MySqlParser.ReturnStatementContext ctx) {
        LOGGER.debug("enterReturnStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitReturnStatement(MySqlParser.ReturnStatementContext ctx) {
        LOGGER.debug("exitReturnStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterWhileStatement(MySqlParser.WhileStatementContext ctx) {
        LOGGER.debug("enterWhileStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitWhileStatement(MySqlParser.WhileStatementContext ctx) {
        LOGGER.debug("exitWhileStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterCloseCursor(MySqlParser.CloseCursorContext ctx) {
        LOGGER.debug("enterCloseCursor <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitCloseCursor(MySqlParser.CloseCursorContext ctx) {
        LOGGER.debug("exitCloseCursor <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterFetchCursor(MySqlParser.FetchCursorContext ctx) {
        LOGGER.debug("enterFetchCursor <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitFetchCursor(MySqlParser.FetchCursorContext ctx) {
        LOGGER.debug("exitFetchCursor <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterOpenCursor(MySqlParser.OpenCursorContext ctx) {
        LOGGER.debug("enterOpenCursor <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitOpenCursor(MySqlParser.OpenCursorContext ctx) {
        LOGGER.debug("exitOpenCursor <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterDeclareVariable(MySqlParser.DeclareVariableContext ctx) {
        LOGGER.debug("enterDeclareVariable <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitDeclareVariable(MySqlParser.DeclareVariableContext ctx) {
        LOGGER.debug("exitDeclareVariable <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterDeclareCondition(MySqlParser.DeclareConditionContext ctx) {
        LOGGER.debug("enterDeclareCondition <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitDeclareCondition(MySqlParser.DeclareConditionContext ctx) {
        LOGGER.debug("exitDeclareCondition <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterDeclareCursor(MySqlParser.DeclareCursorContext ctx) {
        LOGGER.debug("enterDeclareCursor <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitDeclareCursor(MySqlParser.DeclareCursorContext ctx) {
        LOGGER.debug("exitDeclareCursor <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterDeclareHandler(MySqlParser.DeclareHandlerContext ctx) {
        LOGGER.debug("enterDeclareHandler <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitDeclareHandler(MySqlParser.DeclareHandlerContext ctx) {
        LOGGER.debug("exitDeclareHandler <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterHandlerConditionCode(MySqlParser.HandlerConditionCodeContext ctx) {
        LOGGER.debug("enterHandlerConditionCode <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitHandlerConditionCode(MySqlParser.HandlerConditionCodeContext ctx) {
        LOGGER.debug("exitHandlerConditionCode <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterHandlerConditionState(MySqlParser.HandlerConditionStateContext ctx) {
        LOGGER.debug("enterHandlerConditionState <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitHandlerConditionState(MySqlParser.HandlerConditionStateContext ctx) {
        LOGGER.debug("exitHandlerConditionState <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterHandlerConditionName(MySqlParser.HandlerConditionNameContext ctx) {
        LOGGER.debug("enterHandlerConditionName <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitHandlerConditionName(MySqlParser.HandlerConditionNameContext ctx) {
        LOGGER.debug("exitHandlerConditionName <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterHandlerConditionWarning(MySqlParser.HandlerConditionWarningContext ctx) {
        LOGGER.debug("enterHandlerConditionWarning <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitHandlerConditionWarning(MySqlParser.HandlerConditionWarningContext ctx) {
        LOGGER.debug("exitHandlerConditionWarning <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterHandlerConditionNotfound(MySqlParser.HandlerConditionNotfoundContext ctx) {
        LOGGER.debug("enterHandlerConditionNotfound <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitHandlerConditionNotfound(MySqlParser.HandlerConditionNotfoundContext ctx) {
        LOGGER.debug("exitHandlerConditionNotfound <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterHandlerConditionException(MySqlParser.HandlerConditionExceptionContext ctx) {
        LOGGER.debug("enterHandlerConditionException <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitHandlerConditionException(MySqlParser.HandlerConditionExceptionContext ctx) {
        LOGGER.debug("exitHandlerConditionException <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterProcedureSqlStatement(MySqlParser.ProcedureSqlStatementContext ctx) {
        LOGGER.debug("enterProcedureSqlStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitProcedureSqlStatement(MySqlParser.ProcedureSqlStatementContext ctx) {
        LOGGER.debug("exitProcedureSqlStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterCaseAlternative(MySqlParser.CaseAlternativeContext ctx) {
        LOGGER.debug("enterCaseAlternative <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitCaseAlternative(MySqlParser.CaseAlternativeContext ctx) {
        LOGGER.debug("exitCaseAlternative <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterElifAlternative(MySqlParser.ElifAlternativeContext ctx) {
        LOGGER.debug("enterElifAlternative <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitElifAlternative(MySqlParser.ElifAlternativeContext ctx) {
        LOGGER.debug("exitElifAlternative <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterAlterUserMysqlV56(MySqlParser.AlterUserMysqlV56Context ctx) {
        LOGGER.debug("enterAlterUserMysqlV56 <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitAlterUserMysqlV56(MySqlParser.AlterUserMysqlV56Context ctx) {
        LOGGER.debug("exitAlterUserMysqlV56 <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterAlterUserMysqlV57(MySqlParser.AlterUserMysqlV57Context ctx) {
        LOGGER.debug("enterAlterUserMysqlV57 <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitAlterUserMysqlV57(MySqlParser.AlterUserMysqlV57Context ctx) {
        LOGGER.debug("exitAlterUserMysqlV57 <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterCreateUserMysqlV56(MySqlParser.CreateUserMysqlV56Context ctx) {
        LOGGER.debug("enterCreateUserMysqlV56 <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitCreateUserMysqlV56(MySqlParser.CreateUserMysqlV56Context ctx) {
        LOGGER.debug("exitCreateUserMysqlV56 <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterCreateUserMysqlV57(MySqlParser.CreateUserMysqlV57Context ctx) {
        LOGGER.debug("enterCreateUserMysqlV57 <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitCreateUserMysqlV57(MySqlParser.CreateUserMysqlV57Context ctx) {
        LOGGER.debug("exitCreateUserMysqlV57 <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterDropUser(MySqlParser.DropUserContext ctx) {
        LOGGER.debug("enterDropUser <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitDropUser(MySqlParser.DropUserContext ctx) {
        LOGGER.debug("exitDropUser <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterGrantStatement(MySqlParser.GrantStatementContext ctx) {
        LOGGER.debug("enterGrantStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitGrantStatement(MySqlParser.GrantStatementContext ctx) {
        LOGGER.debug("exitGrantStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterGrantProxy(MySqlParser.GrantProxyContext ctx) {
        LOGGER.debug("enterGrantProxy <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitGrantProxy(MySqlParser.GrantProxyContext ctx) {
        LOGGER.debug("exitGrantProxy <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterRenameUser(MySqlParser.RenameUserContext ctx) {
        LOGGER.debug("enterRenameUser <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitRenameUser(MySqlParser.RenameUserContext ctx) {
        LOGGER.debug("exitRenameUser <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterDetailRevoke(MySqlParser.DetailRevokeContext ctx) {
        LOGGER.debug("enterDetailRevoke <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitDetailRevoke(MySqlParser.DetailRevokeContext ctx) {
        LOGGER.debug("exitDetailRevoke <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterShortRevoke(MySqlParser.ShortRevokeContext ctx) {
        LOGGER.debug("enterShortRevoke <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitShortRevoke(MySqlParser.ShortRevokeContext ctx) {
        LOGGER.debug("exitShortRevoke <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterRevokeProxy(MySqlParser.RevokeProxyContext ctx) {
        LOGGER.debug("enterRevokeProxy <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitRevokeProxy(MySqlParser.RevokeProxyContext ctx) {
        LOGGER.debug("exitRevokeProxy <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterSetPasswordStatement(MySqlParser.SetPasswordStatementContext ctx) {
        LOGGER.debug("enterSetPasswordStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitSetPasswordStatement(MySqlParser.SetPasswordStatementContext ctx) {
        LOGGER.debug("exitSetPasswordStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterUserSpecification(MySqlParser.UserSpecificationContext ctx) {
        LOGGER.debug("enterUserSpecification <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitUserSpecification(MySqlParser.UserSpecificationContext ctx) {
        LOGGER.debug("exitUserSpecification <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterPasswordAuthOption(MySqlParser.PasswordAuthOptionContext ctx) {
        LOGGER.debug("enterPasswordAuthOption <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitPasswordAuthOption(MySqlParser.PasswordAuthOptionContext ctx) {
        LOGGER.debug("exitPasswordAuthOption <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterStringAuthOption(MySqlParser.StringAuthOptionContext ctx) {
        LOGGER.debug("enterStringAuthOption <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitStringAuthOption(MySqlParser.StringAuthOptionContext ctx) {
        LOGGER.debug("exitStringAuthOption <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterHashAuthOption(MySqlParser.HashAuthOptionContext ctx) {
        LOGGER.debug("enterHashAuthOption <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitHashAuthOption(MySqlParser.HashAuthOptionContext ctx) {
        LOGGER.debug("exitHashAuthOption <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterSimpleAuthOption(MySqlParser.SimpleAuthOptionContext ctx) {
        LOGGER.debug("enterSimpleAuthOption <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitSimpleAuthOption(MySqlParser.SimpleAuthOptionContext ctx) {
        LOGGER.debug("exitSimpleAuthOption <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterTlsOption(MySqlParser.TlsOptionContext ctx) {
        LOGGER.debug("enterTlsOption <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitTlsOption(MySqlParser.TlsOptionContext ctx) {
        LOGGER.debug("exitTlsOption <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterUserResourceOption(MySqlParser.UserResourceOptionContext ctx) {
        LOGGER.debug("enterUserResourceOption <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitUserResourceOption(MySqlParser.UserResourceOptionContext ctx) {
        LOGGER.debug("exitUserResourceOption <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterUserPasswordOption(MySqlParser.UserPasswordOptionContext ctx) {
        LOGGER.debug("enterUserPasswordOption <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitUserPasswordOption(MySqlParser.UserPasswordOptionContext ctx) {
        LOGGER.debug("exitUserPasswordOption <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterUserLockOption(MySqlParser.UserLockOptionContext ctx) {
        LOGGER.debug("enterUserLockOption <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitUserLockOption(MySqlParser.UserLockOptionContext ctx) {
        LOGGER.debug("exitUserLockOption <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterPrivelegeClause(MySqlParser.PrivelegeClauseContext ctx) {
        LOGGER.debug("enterPrivelegeClause <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitPrivelegeClause(MySqlParser.PrivelegeClauseContext ctx) {
        LOGGER.debug("exitPrivelegeClause <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterPrivilege(MySqlParser.PrivilegeContext ctx) {
        LOGGER.debug("enterPrivilege <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitPrivilege(MySqlParser.PrivilegeContext ctx) {
        LOGGER.debug("exitPrivilege <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterCurrentSchemaPriviLevel(MySqlParser.CurrentSchemaPriviLevelContext ctx) {
        LOGGER.debug("enterCurrentSchemaPriviLevel <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitCurrentSchemaPriviLevel(MySqlParser.CurrentSchemaPriviLevelContext ctx) {
        LOGGER.debug("exitCurrentSchemaPriviLevel <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterGlobalPrivLevel(MySqlParser.GlobalPrivLevelContext ctx) {
        LOGGER.debug("enterGlobalPrivLevel <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitGlobalPrivLevel(MySqlParser.GlobalPrivLevelContext ctx) {
        LOGGER.debug("exitGlobalPrivLevel <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterDefiniteSchemaPrivLevel(MySqlParser.DefiniteSchemaPrivLevelContext ctx) {
        LOGGER.debug("enterDefiniteSchemaPrivLevel <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitDefiniteSchemaPrivLevel(MySqlParser.DefiniteSchemaPrivLevelContext ctx) {
        LOGGER.debug("exitDefiniteSchemaPrivLevel <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterDefiniteFullTablePrivLevel(MySqlParser.DefiniteFullTablePrivLevelContext ctx) {
        LOGGER.debug("enterDefiniteFullTablePrivLevel <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitDefiniteFullTablePrivLevel(MySqlParser.DefiniteFullTablePrivLevelContext ctx) {
        LOGGER.debug("exitDefiniteFullTablePrivLevel <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterDefiniteFullTablePrivLevel2(MySqlParser.DefiniteFullTablePrivLevel2Context ctx) {
        LOGGER.debug("enterDefiniteFullTablePrivLevel2 <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitDefiniteFullTablePrivLevel2(MySqlParser.DefiniteFullTablePrivLevel2Context ctx) {
        LOGGER.debug("exitDefiniteFullTablePrivLevel2 <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterDefiniteTablePrivLevel(MySqlParser.DefiniteTablePrivLevelContext ctx) {
        LOGGER.debug("enterDefiniteTablePrivLevel <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitDefiniteTablePrivLevel(MySqlParser.DefiniteTablePrivLevelContext ctx) {
        LOGGER.debug("exitDefiniteTablePrivLevel <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterRenameUserClause(MySqlParser.RenameUserClauseContext ctx) {
        LOGGER.debug("enterRenameUserClause <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitRenameUserClause(MySqlParser.RenameUserClauseContext ctx) {
        LOGGER.debug("exitRenameUserClause <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterAnalyzeTable(MySqlParser.AnalyzeTableContext ctx) {
        LOGGER.debug("enterAnalyzeTable <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitAnalyzeTable(MySqlParser.AnalyzeTableContext ctx) {
        LOGGER.debug("exitAnalyzeTable <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterCheckTable(MySqlParser.CheckTableContext ctx) {
        LOGGER.debug("enterCheckTable <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitCheckTable(MySqlParser.CheckTableContext ctx) {
        LOGGER.debug("exitCheckTable <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterChecksumTable(MySqlParser.ChecksumTableContext ctx) {
        LOGGER.debug("enterChecksumTable <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitChecksumTable(MySqlParser.ChecksumTableContext ctx) {
        LOGGER.debug("exitChecksumTable <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterOptimizeTable(MySqlParser.OptimizeTableContext ctx) {
        LOGGER.debug("enterOptimizeTable <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitOptimizeTable(MySqlParser.OptimizeTableContext ctx) {
        LOGGER.debug("exitOptimizeTable <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterRepairTable(MySqlParser.RepairTableContext ctx) {
        LOGGER.debug("enterRepairTable <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitRepairTable(MySqlParser.RepairTableContext ctx) {
        LOGGER.debug("exitRepairTable <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterCheckTableOption(MySqlParser.CheckTableOptionContext ctx) {
        LOGGER.debug("enterCheckTableOption <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitCheckTableOption(MySqlParser.CheckTableOptionContext ctx) {
        LOGGER.debug("exitCheckTableOption <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterCreateUdfunction(MySqlParser.CreateUdfunctionContext ctx) {
        LOGGER.debug("enterCreateUdfunction <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitCreateUdfunction(MySqlParser.CreateUdfunctionContext ctx) {
        LOGGER.debug("exitCreateUdfunction <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterInstallPlugin(MySqlParser.InstallPluginContext ctx) {
        LOGGER.debug("enterInstallPlugin <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitInstallPlugin(MySqlParser.InstallPluginContext ctx) {
        LOGGER.debug("exitInstallPlugin <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterUninstallPlugin(MySqlParser.UninstallPluginContext ctx) {
        LOGGER.debug("enterUninstallPlugin <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitUninstallPlugin(MySqlParser.UninstallPluginContext ctx) {
        LOGGER.debug("exitUninstallPlugin <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterSetVariable(MySqlParser.SetVariableContext ctx) {
        LOGGER.debug("enterSetVariable <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitSetVariable(MySqlParser.SetVariableContext ctx) {
        LOGGER.debug("exitSetVariable <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterSetCharset(MySqlParser.SetCharsetContext ctx) {
        LOGGER.debug("enterSetCharset <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitSetCharset(MySqlParser.SetCharsetContext ctx) {
        LOGGER.debug("exitSetCharset <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterSetNames(MySqlParser.SetNamesContext ctx) {
        LOGGER.debug("enterSetNames <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitSetNames(MySqlParser.SetNamesContext ctx) {
        LOGGER.debug("exitSetNames <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterSetPassword(MySqlParser.SetPasswordContext ctx) {
        LOGGER.debug("enterSetPassword <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitSetPassword(MySqlParser.SetPasswordContext ctx) {
        LOGGER.debug("exitSetPassword <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterSetTransaction(MySqlParser.SetTransactionContext ctx) {
        LOGGER.debug("enterSetTransaction <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitSetTransaction(MySqlParser.SetTransactionContext ctx) {
        LOGGER.debug("exitSetTransaction <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterSetAutocommit(MySqlParser.SetAutocommitContext ctx) {
        LOGGER.debug("enterSetAutocommit <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitSetAutocommit(MySqlParser.SetAutocommitContext ctx) {
        LOGGER.debug("exitSetAutocommit <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterSetNewValueInsideTrigger(MySqlParser.SetNewValueInsideTriggerContext ctx) {
        LOGGER.debug("enterSetNewValueInsideTrigger <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitSetNewValueInsideTrigger(MySqlParser.SetNewValueInsideTriggerContext ctx) {
        LOGGER.debug("exitSetNewValueInsideTrigger <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterShowMasterLogs(MySqlParser.ShowMasterLogsContext ctx) {
        LOGGER.debug("enterShowMasterLogs <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitShowMasterLogs(MySqlParser.ShowMasterLogsContext ctx) {
        LOGGER.debug("exitShowMasterLogs <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterShowLogEvents(MySqlParser.ShowLogEventsContext ctx) {
        LOGGER.debug("enterShowLogEvents <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitShowLogEvents(MySqlParser.ShowLogEventsContext ctx) {
        LOGGER.debug("exitShowLogEvents <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterShowObjectFilter(MySqlParser.ShowObjectFilterContext ctx) {
        LOGGER.debug("enterShowObjectFilter <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitShowObjectFilter(MySqlParser.ShowObjectFilterContext ctx) {
        LOGGER.debug("exitShowObjectFilter <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterShowColumns(MySqlParser.ShowColumnsContext ctx) {
        LOGGER.debug("enterShowColumns <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitShowColumns(MySqlParser.ShowColumnsContext ctx) {
        LOGGER.debug("exitShowColumns <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterShowCreateDb(MySqlParser.ShowCreateDbContext ctx) {
        LOGGER.debug("enterShowCreateDb <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitShowCreateDb(MySqlParser.ShowCreateDbContext ctx) {
        LOGGER.debug("exitShowCreateDb <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterShowCreateFullIdObject(MySqlParser.ShowCreateFullIdObjectContext ctx) {
        LOGGER.debug("enterShowCreateFullIdObject <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitShowCreateFullIdObject(MySqlParser.ShowCreateFullIdObjectContext ctx) {
        LOGGER.debug("exitShowCreateFullIdObject <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterShowCreateUser(MySqlParser.ShowCreateUserContext ctx) {
        LOGGER.debug("enterShowCreateUser <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitShowCreateUser(MySqlParser.ShowCreateUserContext ctx) {
        LOGGER.debug("exitShowCreateUser <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterShowEngine(MySqlParser.ShowEngineContext ctx) {
        LOGGER.debug("enterShowEngine <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitShowEngine(MySqlParser.ShowEngineContext ctx) {
        LOGGER.debug("exitShowEngine <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterShowGlobalInfo(MySqlParser.ShowGlobalInfoContext ctx) {
        LOGGER.debug("enterShowGlobalInfo <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitShowGlobalInfo(MySqlParser.ShowGlobalInfoContext ctx) {
        LOGGER.debug("exitShowGlobalInfo <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterShowErrors(MySqlParser.ShowErrorsContext ctx) {
        LOGGER.debug("enterShowErrors <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitShowErrors(MySqlParser.ShowErrorsContext ctx) {
        LOGGER.debug("exitShowErrors <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterShowCountErrors(MySqlParser.ShowCountErrorsContext ctx) {
        LOGGER.debug("enterShowCountErrors <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitShowCountErrors(MySqlParser.ShowCountErrorsContext ctx) {
        LOGGER.debug("exitShowCountErrors <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterShowSchemaFilter(MySqlParser.ShowSchemaFilterContext ctx) {
        LOGGER.debug("enterShowSchemaFilter <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitShowSchemaFilter(MySqlParser.ShowSchemaFilterContext ctx) {
        LOGGER.debug("exitShowSchemaFilter <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterShowRoutine(MySqlParser.ShowRoutineContext ctx) {
        LOGGER.debug("enterShowRoutine <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitShowRoutine(MySqlParser.ShowRoutineContext ctx) {
        LOGGER.debug("exitShowRoutine <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterShowGrants(MySqlParser.ShowGrantsContext ctx) {
        LOGGER.debug("enterShowGrants <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitShowGrants(MySqlParser.ShowGrantsContext ctx) {
        LOGGER.debug("exitShowGrants <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterShowIndexes(MySqlParser.ShowIndexesContext ctx) {
        LOGGER.debug("enterShowIndexes <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitShowIndexes(MySqlParser.ShowIndexesContext ctx) {
        LOGGER.debug("exitShowIndexes <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterShowOpenTables(MySqlParser.ShowOpenTablesContext ctx) {
        LOGGER.debug("enterShowOpenTables <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitShowOpenTables(MySqlParser.ShowOpenTablesContext ctx) {
        LOGGER.debug("exitShowOpenTables <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterShowProfile(MySqlParser.ShowProfileContext ctx) {
        LOGGER.debug("enterShowProfile <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitShowProfile(MySqlParser.ShowProfileContext ctx) {
        LOGGER.debug("exitShowProfile <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterShowSlaveStatus(MySqlParser.ShowSlaveStatusContext ctx) {
        LOGGER.debug("enterShowSlaveStatus <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitShowSlaveStatus(MySqlParser.ShowSlaveStatusContext ctx) {
        LOGGER.debug("exitShowSlaveStatus <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterVariableClause(MySqlParser.VariableClauseContext ctx) {
        LOGGER.debug("enterVariableClause <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitVariableClause(MySqlParser.VariableClauseContext ctx) {
        LOGGER.debug("exitVariableClause <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterShowCommonEntity(MySqlParser.ShowCommonEntityContext ctx) {
        LOGGER.debug("enterShowCommonEntity <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitShowCommonEntity(MySqlParser.ShowCommonEntityContext ctx) {
        LOGGER.debug("exitShowCommonEntity <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterShowFilter(MySqlParser.ShowFilterContext ctx) {
        LOGGER.debug("enterShowFilter <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitShowFilter(MySqlParser.ShowFilterContext ctx) {
        LOGGER.debug("exitShowFilter <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterShowGlobalInfoClause(MySqlParser.ShowGlobalInfoClauseContext ctx) {
        LOGGER.debug("enterShowGlobalInfoClause <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitShowGlobalInfoClause(MySqlParser.ShowGlobalInfoClauseContext ctx) {
        LOGGER.debug("exitShowGlobalInfoClause <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterShowSchemaEntity(MySqlParser.ShowSchemaEntityContext ctx) {
        LOGGER.debug("enterShowSchemaEntity <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitShowSchemaEntity(MySqlParser.ShowSchemaEntityContext ctx) {
        LOGGER.debug("exitShowSchemaEntity <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterShowProfileType(MySqlParser.ShowProfileTypeContext ctx) {
        LOGGER.debug("enterShowProfileType <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitShowProfileType(MySqlParser.ShowProfileTypeContext ctx) {
        LOGGER.debug("exitShowProfileType <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterBinlogStatement(MySqlParser.BinlogStatementContext ctx) {
        LOGGER.debug("enterBinlogStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitBinlogStatement(MySqlParser.BinlogStatementContext ctx) {
        LOGGER.debug("exitBinlogStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterCacheIndexStatement(MySqlParser.CacheIndexStatementContext ctx) {
        LOGGER.debug("enterCacheIndexStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitCacheIndexStatement(MySqlParser.CacheIndexStatementContext ctx) {
        LOGGER.debug("exitCacheIndexStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterFlushStatement(MySqlParser.FlushStatementContext ctx) {
        LOGGER.debug("enterFlushStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitFlushStatement(MySqlParser.FlushStatementContext ctx) {
        LOGGER.debug("exitFlushStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterKillStatement(MySqlParser.KillStatementContext ctx) {
        LOGGER.debug("enterKillStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitKillStatement(MySqlParser.KillStatementContext ctx) {
        LOGGER.debug("exitKillStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterLoadIndexIntoCache(MySqlParser.LoadIndexIntoCacheContext ctx) {
        LOGGER.debug("enterLoadIndexIntoCache <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitLoadIndexIntoCache(MySqlParser.LoadIndexIntoCacheContext ctx) {
        LOGGER.debug("exitLoadIndexIntoCache <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterResetStatement(MySqlParser.ResetStatementContext ctx) {
        LOGGER.debug("enterResetStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitResetStatement(MySqlParser.ResetStatementContext ctx) {
        LOGGER.debug("exitResetStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterShutdownStatement(MySqlParser.ShutdownStatementContext ctx) {
        LOGGER.debug("enterShutdownStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitShutdownStatement(MySqlParser.ShutdownStatementContext ctx) {
        LOGGER.debug("exitShutdownStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterTableIndexes(MySqlParser.TableIndexesContext ctx) {
        LOGGER.debug("enterTableIndexes <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitTableIndexes(MySqlParser.TableIndexesContext ctx) {
        LOGGER.debug("exitTableIndexes <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterSimpleFlushOption(MySqlParser.SimpleFlushOptionContext ctx) {
        LOGGER.debug("enterSimpleFlushOption <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitSimpleFlushOption(MySqlParser.SimpleFlushOptionContext ctx) {
        LOGGER.debug("exitSimpleFlushOption <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterChannelFlushOption(MySqlParser.ChannelFlushOptionContext ctx) {
        LOGGER.debug("enterChannelFlushOption <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitChannelFlushOption(MySqlParser.ChannelFlushOptionContext ctx) {
        LOGGER.debug("exitChannelFlushOption <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterTableFlushOption(MySqlParser.TableFlushOptionContext ctx) {
        LOGGER.debug("enterTableFlushOption <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitTableFlushOption(MySqlParser.TableFlushOptionContext ctx) {
        LOGGER.debug("exitTableFlushOption <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterFlushTableOption(MySqlParser.FlushTableOptionContext ctx) {
        LOGGER.debug("enterFlushTableOption <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitFlushTableOption(MySqlParser.FlushTableOptionContext ctx) {
        LOGGER.debug("exitFlushTableOption <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterLoadedTableIndexes(MySqlParser.LoadedTableIndexesContext ctx) {
        LOGGER.debug("enterLoadedTableIndexes <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitLoadedTableIndexes(MySqlParser.LoadedTableIndexesContext ctx) {
        LOGGER.debug("exitLoadedTableIndexes <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterSimpleDescribeStatement(MySqlParser.SimpleDescribeStatementContext ctx) {
        LOGGER.debug("enterSimpleDescribeStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitSimpleDescribeStatement(MySqlParser.SimpleDescribeStatementContext ctx) {
        LOGGER.debug("exitSimpleDescribeStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterFullDescribeStatement(MySqlParser.FullDescribeStatementContext ctx) {
        LOGGER.debug("enterFullDescribeStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitFullDescribeStatement(MySqlParser.FullDescribeStatementContext ctx) {
        LOGGER.debug("exitFullDescribeStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterHelpStatement(MySqlParser.HelpStatementContext ctx) {
        LOGGER.debug("enterHelpStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitHelpStatement(MySqlParser.HelpStatementContext ctx) {
        LOGGER.debug("exitHelpStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterUseStatement(MySqlParser.UseStatementContext ctx) {
        LOGGER.debug("enterUseStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitUseStatement(MySqlParser.UseStatementContext ctx) {
        LOGGER.debug("exitUseStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterSignalStatement(MySqlParser.SignalStatementContext ctx) {
        LOGGER.debug("enterSignalStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitSignalStatement(MySqlParser.SignalStatementContext ctx) {
        LOGGER.debug("exitSignalStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterResignalStatement(MySqlParser.ResignalStatementContext ctx) {
        LOGGER.debug("enterResignalStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitResignalStatement(MySqlParser.ResignalStatementContext ctx) {
        LOGGER.debug("exitResignalStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterSignalConditionInformation(MySqlParser.SignalConditionInformationContext ctx) {
        LOGGER.debug("enterSignalConditionInformation <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitSignalConditionInformation(MySqlParser.SignalConditionInformationContext ctx) {
        LOGGER.debug("exitSignalConditionInformation <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterDiagnosticsStatement(MySqlParser.DiagnosticsStatementContext ctx) {
        LOGGER.debug("enterDiagnosticsStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitDiagnosticsStatement(MySqlParser.DiagnosticsStatementContext ctx) {
        LOGGER.debug("exitDiagnosticsStatement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterDiagnosticsConditionInformationName(MySqlParser.DiagnosticsConditionInformationNameContext ctx) {
        LOGGER.debug("enterDiagnosticsConditionInformationName <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitDiagnosticsConditionInformationName(MySqlParser.DiagnosticsConditionInformationNameContext ctx) {
        LOGGER.debug("exitDiagnosticsConditionInformationName <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterDescribeStatements(MySqlParser.DescribeStatementsContext ctx) {
        LOGGER.debug("enterDescribeStatements <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitDescribeStatements(MySqlParser.DescribeStatementsContext ctx) {
        LOGGER.debug("exitDescribeStatements <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterDescribeConnection(MySqlParser.DescribeConnectionContext ctx) {
        LOGGER.debug("enterDescribeConnection <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitDescribeConnection(MySqlParser.DescribeConnectionContext ctx) {
        LOGGER.debug("exitDescribeConnection <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterFullId(MySqlParser.FullIdContext ctx) {
        LOGGER.debug("enterFullId <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitFullId(MySqlParser.FullIdContext ctx) {
        LOGGER.debug("exitFullId <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterTableName(MySqlParser.TableNameContext ctx) {
        LOGGER.debug("enterTableName <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitTableName(MySqlParser.TableNameContext ctx) {
        LOGGER.debug("exitTableName <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterFullColumnName(MySqlParser.FullColumnNameContext ctx) {
        LOGGER.debug("enterFullColumnName <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitFullColumnName(MySqlParser.FullColumnNameContext ctx) {
        LOGGER.debug("exitFullColumnName <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterIndexColumnName(MySqlParser.IndexColumnNameContext ctx) {
        LOGGER.debug("enterIndexColumnName <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitIndexColumnName(MySqlParser.IndexColumnNameContext ctx) {
        LOGGER.debug("exitIndexColumnName <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterUserName(MySqlParser.UserNameContext ctx) {
        LOGGER.debug("enterUserName <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitUserName(MySqlParser.UserNameContext ctx) {
        LOGGER.debug("exitUserName <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterMysqlVariable(MySqlParser.MysqlVariableContext ctx) {
        LOGGER.debug("enterMysqlVariable <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitMysqlVariable(MySqlParser.MysqlVariableContext ctx) {
        LOGGER.debug("exitMysqlVariable <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterCharsetName(MySqlParser.CharsetNameContext ctx) {
        LOGGER.debug("enterCharsetName <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitCharsetName(MySqlParser.CharsetNameContext ctx) {
        LOGGER.debug("exitCharsetName <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterCollationName(MySqlParser.CollationNameContext ctx) {
        LOGGER.debug("enterCollationName <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitCollationName(MySqlParser.CollationNameContext ctx) {
        LOGGER.debug("exitCollationName <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterEngineName(MySqlParser.EngineNameContext ctx) {
        LOGGER.debug("enterEngineName <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitEngineName(MySqlParser.EngineNameContext ctx) {
        LOGGER.debug("exitEngineName <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterUuidSet(MySqlParser.UuidSetContext ctx) {
        LOGGER.debug("enterUuidSet <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitUuidSet(MySqlParser.UuidSetContext ctx) {
        LOGGER.debug("exitUuidSet <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterXid(MySqlParser.XidContext ctx) {
        LOGGER.debug("enterXid <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitXid(MySqlParser.XidContext ctx) {
        LOGGER.debug("exitXid <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterXuidStringId(MySqlParser.XuidStringIdContext ctx) {
        LOGGER.debug("enterXuidStringId <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitXuidStringId(MySqlParser.XuidStringIdContext ctx) {
        LOGGER.debug("exitXuidStringId <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterAuthPlugin(MySqlParser.AuthPluginContext ctx) {
        LOGGER.debug("enterAuthPlugin <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitAuthPlugin(MySqlParser.AuthPluginContext ctx) {
        LOGGER.debug("exitAuthPlugin <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterUid(MySqlParser.UidContext ctx) {
        LOGGER.debug("enterUid <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitUid(MySqlParser.UidContext ctx) {
        LOGGER.debug("exitUid <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterSimpleId(MySqlParser.SimpleIdContext ctx) {
        LOGGER.debug("enterSimpleId <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitSimpleId(MySqlParser.SimpleIdContext ctx) {
        LOGGER.debug("exitSimpleId <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterDottedId(MySqlParser.DottedIdContext ctx) {
        LOGGER.debug("enterDottedId <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitDottedId(MySqlParser.DottedIdContext ctx) {
        LOGGER.debug("exitDottedId <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterDecimalLiteral(MySqlParser.DecimalLiteralContext ctx) {
        LOGGER.debug("enterDecimalLiteral <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitDecimalLiteral(MySqlParser.DecimalLiteralContext ctx) {
        LOGGER.debug("exitDecimalLiteral <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterFileSizeLiteral(MySqlParser.FileSizeLiteralContext ctx) {
        LOGGER.debug("enterFileSizeLiteral <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitFileSizeLiteral(MySqlParser.FileSizeLiteralContext ctx) {
        LOGGER.debug("exitFileSizeLiteral <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterStringLiteral(MySqlParser.StringLiteralContext ctx) {
        LOGGER.debug("enterStringLiteral <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitStringLiteral(MySqlParser.StringLiteralContext ctx) {
        LOGGER.debug("exitStringLiteral <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterBooleanLiteral(MySqlParser.BooleanLiteralContext ctx) {
        LOGGER.debug("enterBooleanLiteral <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitBooleanLiteral(MySqlParser.BooleanLiteralContext ctx) {
        LOGGER.debug("exitBooleanLiteral <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterHexadecimalLiteral(MySqlParser.HexadecimalLiteralContext ctx) {
        LOGGER.debug("enterHexadecimalLiteral <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitHexadecimalLiteral(MySqlParser.HexadecimalLiteralContext ctx) {
        LOGGER.debug("exitHexadecimalLiteral <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterNullNotnull(MySqlParser.NullNotnullContext ctx) {
        LOGGER.debug("enterNullNotnull <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitNullNotnull(MySqlParser.NullNotnullContext ctx) {
        LOGGER.debug("exitNullNotnull <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterConstant(MySqlParser.ConstantContext ctx) {
        LOGGER.debug("enterConstant <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitConstant(MySqlParser.ConstantContext ctx) {
        LOGGER.debug("exitConstant <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterStringDataType(MySqlParser.StringDataTypeContext ctx) {
        LOGGER.debug("enterStringDataType <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitStringDataType(MySqlParser.StringDataTypeContext ctx) {
        LOGGER.debug("exitStringDataType <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterNationalStringDataType(MySqlParser.NationalStringDataTypeContext ctx) {
        LOGGER.debug("enterNationalStringDataType <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitNationalStringDataType(MySqlParser.NationalStringDataTypeContext ctx) {
        LOGGER.debug("exitNationalStringDataType <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterNationalVaryingStringDataType(MySqlParser.NationalVaryingStringDataTypeContext ctx) {
        LOGGER.debug("enterNationalVaryingStringDataType <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitNationalVaryingStringDataType(MySqlParser.NationalVaryingStringDataTypeContext ctx) {
        LOGGER.debug("exitNationalVaryingStringDataType <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterDimensionDataType(MySqlParser.DimensionDataTypeContext ctx) {
        LOGGER.debug("enterDimensionDataType <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitDimensionDataType(MySqlParser.DimensionDataTypeContext ctx) {
        LOGGER.debug("exitDimensionDataType <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterSimpleDataType(MySqlParser.SimpleDataTypeContext ctx) {
        LOGGER.debug("enterSimpleDataType <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitSimpleDataType(MySqlParser.SimpleDataTypeContext ctx) {
        LOGGER.debug("exitSimpleDataType <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterCollectionDataType(MySqlParser.CollectionDataTypeContext ctx) {
        LOGGER.debug("enterCollectionDataType <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitCollectionDataType(MySqlParser.CollectionDataTypeContext ctx) {
        LOGGER.debug("exitCollectionDataType <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterSpatialDataType(MySqlParser.SpatialDataTypeContext ctx) {
        LOGGER.debug("enterSpatialDataType <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitSpatialDataType(MySqlParser.SpatialDataTypeContext ctx) {
        LOGGER.debug("exitSpatialDataType <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterLongVarcharDataType(MySqlParser.LongVarcharDataTypeContext ctx) {
        LOGGER.debug("enterLongVarcharDataType <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitLongVarcharDataType(MySqlParser.LongVarcharDataTypeContext ctx) {
        LOGGER.debug("exitLongVarcharDataType <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterLongVarbinaryDataType(MySqlParser.LongVarbinaryDataTypeContext ctx) {
        LOGGER.debug("enterLongVarbinaryDataType <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitLongVarbinaryDataType(MySqlParser.LongVarbinaryDataTypeContext ctx) {
        LOGGER.debug("exitLongVarbinaryDataType <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterCollectionOptions(MySqlParser.CollectionOptionsContext ctx) {
        LOGGER.debug("enterCollectionOptions <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitCollectionOptions(MySqlParser.CollectionOptionsContext ctx) {
        LOGGER.debug("exitCollectionOptions <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterConvertedDataType(MySqlParser.ConvertedDataTypeContext ctx) {
        LOGGER.debug("enterConvertedDataType <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitConvertedDataType(MySqlParser.ConvertedDataTypeContext ctx) {
        LOGGER.debug("exitConvertedDataType <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterLengthOneDimension(MySqlParser.LengthOneDimensionContext ctx) {
        LOGGER.debug("enterLengthOneDimension <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitLengthOneDimension(MySqlParser.LengthOneDimensionContext ctx) {
        LOGGER.debug("exitLengthOneDimension <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterLengthTwoDimension(MySqlParser.LengthTwoDimensionContext ctx) {
        LOGGER.debug("enterLengthTwoDimension <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitLengthTwoDimension(MySqlParser.LengthTwoDimensionContext ctx) {
        LOGGER.debug("exitLengthTwoDimension <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterLengthTwoOptionalDimension(MySqlParser.LengthTwoOptionalDimensionContext ctx) {
        LOGGER.debug("enterLengthTwoOptionalDimension <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitLengthTwoOptionalDimension(MySqlParser.LengthTwoOptionalDimensionContext ctx) {
        LOGGER.debug("exitLengthTwoOptionalDimension <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterUidList(MySqlParser.UidListContext ctx) {
        LOGGER.debug("enterUidList <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitUidList(MySqlParser.UidListContext ctx) {
        LOGGER.debug("exitUidList <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterTables(MySqlParser.TablesContext ctx) {
        LOGGER.debug("enterTables <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitTables(MySqlParser.TablesContext ctx) {
        LOGGER.debug("exitTables <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterIndexColumnNames(MySqlParser.IndexColumnNamesContext ctx) {
        LOGGER.debug("enterIndexColumnNames <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitIndexColumnNames(MySqlParser.IndexColumnNamesContext ctx) {
        LOGGER.debug("exitIndexColumnNames <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterExpressions(MySqlParser.ExpressionsContext ctx) {
        LOGGER.debug("enterExpressions <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitExpressions(MySqlParser.ExpressionsContext ctx) {
        LOGGER.debug("exitExpressions <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterExpressionsWithDefaults(MySqlParser.ExpressionsWithDefaultsContext ctx) {
        LOGGER.debug("enterExpressionsWithDefaults <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitExpressionsWithDefaults(MySqlParser.ExpressionsWithDefaultsContext ctx) {
        LOGGER.debug("exitExpressionsWithDefaults <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterConstants(MySqlParser.ConstantsContext ctx) {
        LOGGER.debug("enterConstants <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitConstants(MySqlParser.ConstantsContext ctx) {
        LOGGER.debug("exitConstants <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterSimpleStrings(MySqlParser.SimpleStringsContext ctx) {
        LOGGER.debug("enterSimpleStrings <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitSimpleStrings(MySqlParser.SimpleStringsContext ctx) {
        LOGGER.debug("exitSimpleStrings <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterUserVariables(MySqlParser.UserVariablesContext ctx) {
        LOGGER.debug("enterUserVariables <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitUserVariables(MySqlParser.UserVariablesContext ctx) {
        LOGGER.debug("exitUserVariables <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterDefaultValue(MySqlParser.DefaultValueContext ctx) {
        LOGGER.debug("enterDefaultValue <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitDefaultValue(MySqlParser.DefaultValueContext ctx) {
        LOGGER.debug("exitDefaultValue <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterCurrentTimestamp(MySqlParser.CurrentTimestampContext ctx) {
        LOGGER.debug("enterCurrentTimestamp <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitCurrentTimestamp(MySqlParser.CurrentTimestampContext ctx) {
        LOGGER.debug("exitCurrentTimestamp <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterExpressionOrDefault(MySqlParser.ExpressionOrDefaultContext ctx) {
        LOGGER.debug("enterExpressionOrDefault <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitExpressionOrDefault(MySqlParser.ExpressionOrDefaultContext ctx) {
        LOGGER.debug("exitExpressionOrDefault <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterIfExists(MySqlParser.IfExistsContext ctx) {
        LOGGER.debug("enterIfExists <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitIfExists(MySqlParser.IfExistsContext ctx) {
        LOGGER.debug("exitIfExists <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterIfNotExists(MySqlParser.IfNotExistsContext ctx) {
        LOGGER.debug("enterIfNotExists <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitIfNotExists(MySqlParser.IfNotExistsContext ctx) {
        LOGGER.debug("exitIfNotExists <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterSpecificFunctionCall(MySqlParser.SpecificFunctionCallContext ctx) {
        LOGGER.debug("enterSpecificFunctionCall <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitSpecificFunctionCall(MySqlParser.SpecificFunctionCallContext ctx) {
        LOGGER.debug("exitSpecificFunctionCall <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterAggregateFunctionCall(MySqlParser.AggregateFunctionCallContext ctx) {
        LOGGER.debug("enterAggregateFunctionCall <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitAggregateFunctionCall(MySqlParser.AggregateFunctionCallContext ctx) {
        LOGGER.debug("exitAggregateFunctionCall <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterScalarFunctionCall(MySqlParser.ScalarFunctionCallContext ctx) {
        LOGGER.debug("enterScalarFunctionCall <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitScalarFunctionCall(MySqlParser.ScalarFunctionCallContext ctx) {
        LOGGER.debug("exitScalarFunctionCall <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterUdfFunctionCall(MySqlParser.UdfFunctionCallContext ctx) {
        LOGGER.debug("enterUdfFunctionCall <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitUdfFunctionCall(MySqlParser.UdfFunctionCallContext ctx) {
        LOGGER.debug("exitUdfFunctionCall <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterPasswordFunctionCall(MySqlParser.PasswordFunctionCallContext ctx) {
        LOGGER.debug("enterPasswordFunctionCall <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitPasswordFunctionCall(MySqlParser.PasswordFunctionCallContext ctx) {
        LOGGER.debug("exitPasswordFunctionCall <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterSimpleFunctionCall(MySqlParser.SimpleFunctionCallContext ctx) {
        LOGGER.debug("enterSimpleFunctionCall <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitSimpleFunctionCall(MySqlParser.SimpleFunctionCallContext ctx) {
        LOGGER.debug("exitSimpleFunctionCall <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterDataTypeFunctionCall(MySqlParser.DataTypeFunctionCallContext ctx) {
        LOGGER.debug("enterDataTypeFunctionCall <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitDataTypeFunctionCall(MySqlParser.DataTypeFunctionCallContext ctx) {
        LOGGER.debug("exitDataTypeFunctionCall <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterValuesFunctionCall(MySqlParser.ValuesFunctionCallContext ctx) {
        LOGGER.debug("enterValuesFunctionCall <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitValuesFunctionCall(MySqlParser.ValuesFunctionCallContext ctx) {
        LOGGER.debug("exitValuesFunctionCall <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterCaseFunctionCall(MySqlParser.CaseFunctionCallContext ctx) {
        LOGGER.debug("enterCaseFunctionCall <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitCaseFunctionCall(MySqlParser.CaseFunctionCallContext ctx) {
        LOGGER.debug("exitCaseFunctionCall <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterCharFunctionCall(MySqlParser.CharFunctionCallContext ctx) {
        LOGGER.debug("enterCharFunctionCall <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitCharFunctionCall(MySqlParser.CharFunctionCallContext ctx) {
        LOGGER.debug("exitCharFunctionCall <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterPositionFunctionCall(MySqlParser.PositionFunctionCallContext ctx) {
        LOGGER.debug("enterPositionFunctionCall <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitPositionFunctionCall(MySqlParser.PositionFunctionCallContext ctx) {
        LOGGER.debug("exitPositionFunctionCall <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterSubstrFunctionCall(MySqlParser.SubstrFunctionCallContext ctx) {
        LOGGER.debug("enterSubstrFunctionCall <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitSubstrFunctionCall(MySqlParser.SubstrFunctionCallContext ctx) {
        LOGGER.debug("exitSubstrFunctionCall <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterTrimFunctionCall(MySqlParser.TrimFunctionCallContext ctx) {
        LOGGER.debug("enterTrimFunctionCall <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitTrimFunctionCall(MySqlParser.TrimFunctionCallContext ctx) {
        LOGGER.debug("exitTrimFunctionCall <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterWeightFunctionCall(MySqlParser.WeightFunctionCallContext ctx) {
        LOGGER.debug("enterWeightFunctionCall <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitWeightFunctionCall(MySqlParser.WeightFunctionCallContext ctx) {
        LOGGER.debug("exitWeightFunctionCall <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterExtractFunctionCall(MySqlParser.ExtractFunctionCallContext ctx) {
        LOGGER.debug("enterExtractFunctionCall <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitExtractFunctionCall(MySqlParser.ExtractFunctionCallContext ctx) {
        LOGGER.debug("exitExtractFunctionCall <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterGetFormatFunctionCall(MySqlParser.GetFormatFunctionCallContext ctx) {
        LOGGER.debug("enterGetFormatFunctionCall <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitGetFormatFunctionCall(MySqlParser.GetFormatFunctionCallContext ctx) {
        LOGGER.debug("exitGetFormatFunctionCall <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterCaseFuncAlternative(MySqlParser.CaseFuncAlternativeContext ctx) {
        LOGGER.debug("enterCaseFuncAlternative <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitCaseFuncAlternative(MySqlParser.CaseFuncAlternativeContext ctx) {
        LOGGER.debug("exitCaseFuncAlternative <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterLevelWeightList(MySqlParser.LevelWeightListContext ctx) {
        LOGGER.debug("enterLevelWeightList <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitLevelWeightList(MySqlParser.LevelWeightListContext ctx) {
        LOGGER.debug("exitLevelWeightList <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterLevelWeightRange(MySqlParser.LevelWeightRangeContext ctx) {
        LOGGER.debug("enterLevelWeightRange <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitLevelWeightRange(MySqlParser.LevelWeightRangeContext ctx) {
        LOGGER.debug("exitLevelWeightRange <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterLevelInWeightListElement(MySqlParser.LevelInWeightListElementContext ctx) {
        LOGGER.debug("enterLevelInWeightListElement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitLevelInWeightListElement(MySqlParser.LevelInWeightListElementContext ctx) {
        LOGGER.debug("exitLevelInWeightListElement <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterAggregateWindowedFunction(MySqlParser.AggregateWindowedFunctionContext ctx) {
        LOGGER.debug("enterAggregateWindowedFunction <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitAggregateWindowedFunction(MySqlParser.AggregateWindowedFunctionContext ctx) {
        LOGGER.debug("exitAggregateWindowedFunction <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterScalarFunctionName(MySqlParser.ScalarFunctionNameContext ctx) {
        LOGGER.debug("enterScalarFunctionName <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitScalarFunctionName(MySqlParser.ScalarFunctionNameContext ctx) {
        LOGGER.debug("exitScalarFunctionName <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterPasswordFunctionClause(MySqlParser.PasswordFunctionClauseContext ctx) {
        LOGGER.debug("enterPasswordFunctionClause <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitPasswordFunctionClause(MySqlParser.PasswordFunctionClauseContext ctx) {
        LOGGER.debug("exitPasswordFunctionClause <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterFunctionArgs(MySqlParser.FunctionArgsContext ctx) {
        LOGGER.debug("enterFunctionArgs <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitFunctionArgs(MySqlParser.FunctionArgsContext ctx) {
        LOGGER.debug("exitFunctionArgs <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterFunctionArg(MySqlParser.FunctionArgContext ctx) {
        LOGGER.debug("enterFunctionArg <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitFunctionArg(MySqlParser.FunctionArgContext ctx) {
        LOGGER.debug("exitFunctionArg <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterIsExpression(MySqlParser.IsExpressionContext ctx) {
        LOGGER.debug("enterIsExpression <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitIsExpression(MySqlParser.IsExpressionContext ctx) {
        LOGGER.debug("exitIsExpression <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterNotExpression(MySqlParser.NotExpressionContext ctx) {
        LOGGER.debug("enterNotExpression <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitNotExpression(MySqlParser.NotExpressionContext ctx) {
        LOGGER.debug("exitNotExpression <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterLogicalExpression(MySqlParser.LogicalExpressionContext ctx) {
        LOGGER.debug("enterLogicalExpression <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitLogicalExpression(MySqlParser.LogicalExpressionContext ctx) {
        LOGGER.debug("exitLogicalExpression <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterPredicateExpression(MySqlParser.PredicateExpressionContext ctx) {
        LOGGER.debug("enterPredicateExpression <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitPredicateExpression(MySqlParser.PredicateExpressionContext ctx) {
        LOGGER.debug("exitPredicateExpression <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterSoundsLikePredicate(MySqlParser.SoundsLikePredicateContext ctx) {
        LOGGER.debug("enterSoundsLikePredicate <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitSoundsLikePredicate(MySqlParser.SoundsLikePredicateContext ctx) {
        LOGGER.debug("exitSoundsLikePredicate <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterExpressionAtomPredicate(MySqlParser.ExpressionAtomPredicateContext ctx) {
        LOGGER.debug("enterExpressionAtomPredicate <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitExpressionAtomPredicate(MySqlParser.ExpressionAtomPredicateContext ctx) {
        LOGGER.debug("exitExpressionAtomPredicate <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterJsonMemberOfPredicate(MySqlParser.JsonMemberOfPredicateContext ctx) {
        LOGGER.debug("enterJsonMemberOfPredicate <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitJsonMemberOfPredicate(MySqlParser.JsonMemberOfPredicateContext ctx) {
        LOGGER.debug("exitJsonMemberOfPredicate <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterInPredicate(MySqlParser.InPredicateContext ctx) {
        LOGGER.debug("enterInPredicate <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitInPredicate(MySqlParser.InPredicateContext ctx) {
        LOGGER.debug("exitInPredicate <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterSubqueryComparasionPredicate(MySqlParser.SubqueryComparasionPredicateContext ctx) {
        LOGGER.debug("enterSubqueryComparasionPredicate <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitSubqueryComparasionPredicate(MySqlParser.SubqueryComparasionPredicateContext ctx) {
        LOGGER.debug("exitSubqueryComparasionPredicate <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterBetweenPredicate(MySqlParser.BetweenPredicateContext ctx) {
        LOGGER.debug("enterBetweenPredicate <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitBetweenPredicate(MySqlParser.BetweenPredicateContext ctx) {
        LOGGER.debug("exitBetweenPredicate <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterBinaryComparasionPredicate(MySqlParser.BinaryComparasionPredicateContext ctx) {
        LOGGER.debug("enterBinaryComparasionPredicate <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitBinaryComparasionPredicate(MySqlParser.BinaryComparasionPredicateContext ctx) {
        LOGGER.debug("exitBinaryComparasionPredicate <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterIsNullPredicate(MySqlParser.IsNullPredicateContext ctx) {
        LOGGER.debug("enterIsNullPredicate <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitIsNullPredicate(MySqlParser.IsNullPredicateContext ctx) {
        LOGGER.debug("exitIsNullPredicate <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterLikePredicate(MySqlParser.LikePredicateContext ctx) {
        LOGGER.debug("enterLikePredicate <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitLikePredicate(MySqlParser.LikePredicateContext ctx) {
        LOGGER.debug("exitLikePredicate <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterRegexpPredicate(MySqlParser.RegexpPredicateContext ctx) {
        LOGGER.debug("enterRegexpPredicate <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitRegexpPredicate(MySqlParser.RegexpPredicateContext ctx) {
        LOGGER.debug("exitRegexpPredicate <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterUnaryExpressionAtom(MySqlParser.UnaryExpressionAtomContext ctx) {
        LOGGER.debug("enterUnaryExpressionAtom <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitUnaryExpressionAtom(MySqlParser.UnaryExpressionAtomContext ctx) {
        LOGGER.debug("exitUnaryExpressionAtom <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterCollateExpressionAtom(MySqlParser.CollateExpressionAtomContext ctx) {
        LOGGER.debug("enterCollateExpressionAtom <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitCollateExpressionAtom(MySqlParser.CollateExpressionAtomContext ctx) {
        LOGGER.debug("exitCollateExpressionAtom <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterSubqueryExpessionAtom(MySqlParser.SubqueryExpessionAtomContext ctx) {
        LOGGER.debug("enterSubqueryExpessionAtom <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitSubqueryExpessionAtom(MySqlParser.SubqueryExpessionAtomContext ctx) {
        LOGGER.debug("exitSubqueryExpessionAtom <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterMysqlVariableExpressionAtom(MySqlParser.MysqlVariableExpressionAtomContext ctx) {
        LOGGER.debug("enterMysqlVariableExpressionAtom <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitMysqlVariableExpressionAtom(MySqlParser.MysqlVariableExpressionAtomContext ctx) {
        LOGGER.debug("exitMysqlVariableExpressionAtom <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterNestedExpressionAtom(MySqlParser.NestedExpressionAtomContext ctx) {
        LOGGER.debug("enterNestedExpressionAtom <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitNestedExpressionAtom(MySqlParser.NestedExpressionAtomContext ctx) {
        LOGGER.debug("exitNestedExpressionAtom <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterNestedRowExpressionAtom(MySqlParser.NestedRowExpressionAtomContext ctx) {
        LOGGER.debug("enterNestedRowExpressionAtom <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitNestedRowExpressionAtom(MySqlParser.NestedRowExpressionAtomContext ctx) {
        LOGGER.debug("exitNestedRowExpressionAtom <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterMathExpressionAtom(MySqlParser.MathExpressionAtomContext ctx) {
        LOGGER.debug("enterMathExpressionAtom <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitMathExpressionAtom(MySqlParser.MathExpressionAtomContext ctx) {
        LOGGER.debug("exitMathExpressionAtom <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterIntervalExpressionAtom(MySqlParser.IntervalExpressionAtomContext ctx) {
        LOGGER.debug("enterIntervalExpressionAtom <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitIntervalExpressionAtom(MySqlParser.IntervalExpressionAtomContext ctx) {
        LOGGER.debug("exitIntervalExpressionAtom <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterJsonExpressionAtom(MySqlParser.JsonExpressionAtomContext ctx) {
        LOGGER.debug("enterJsonExpressionAtom <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitJsonExpressionAtom(MySqlParser.JsonExpressionAtomContext ctx) {
        LOGGER.debug("exitJsonExpressionAtom <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterExistsExpessionAtom(MySqlParser.ExistsExpessionAtomContext ctx) {
        LOGGER.debug("enterExistsExpessionAtom <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitExistsExpessionAtom(MySqlParser.ExistsExpessionAtomContext ctx) {
        LOGGER.debug("exitExistsExpessionAtom <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterConstantExpressionAtom(MySqlParser.ConstantExpressionAtomContext ctx) {
        LOGGER.debug("enterConstantExpressionAtom <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitConstantExpressionAtom(MySqlParser.ConstantExpressionAtomContext ctx) {
        LOGGER.debug("exitConstantExpressionAtom <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterFunctionCallExpressionAtom(MySqlParser.FunctionCallExpressionAtomContext ctx) {
        LOGGER.debug("enterFunctionCallExpressionAtom <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitFunctionCallExpressionAtom(MySqlParser.FunctionCallExpressionAtomContext ctx) {
        LOGGER.debug("exitFunctionCallExpressionAtom <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterBinaryExpressionAtom(MySqlParser.BinaryExpressionAtomContext ctx) {
        LOGGER.debug("enterBinaryExpressionAtom <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitBinaryExpressionAtom(MySqlParser.BinaryExpressionAtomContext ctx) {
        LOGGER.debug("exitBinaryExpressionAtom <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterFullColumnNameExpressionAtom(MySqlParser.FullColumnNameExpressionAtomContext ctx) {
        LOGGER.debug("enterFullColumnNameExpressionAtom <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitFullColumnNameExpressionAtom(MySqlParser.FullColumnNameExpressionAtomContext ctx) {
        LOGGER.debug("exitFullColumnNameExpressionAtom <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterBitExpressionAtom(MySqlParser.BitExpressionAtomContext ctx) {
        LOGGER.debug("enterBitExpressionAtom <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitBitExpressionAtom(MySqlParser.BitExpressionAtomContext ctx) {
        LOGGER.debug("exitBitExpressionAtom <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterUnaryOperator(MySqlParser.UnaryOperatorContext ctx) {
        LOGGER.debug("enterUnaryOperator <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitUnaryOperator(MySqlParser.UnaryOperatorContext ctx) {
        LOGGER.debug("exitUnaryOperator <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterComparisonOperator(MySqlParser.ComparisonOperatorContext ctx) {
        LOGGER.debug("enterComparisonOperator <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitComparisonOperator(MySqlParser.ComparisonOperatorContext ctx) {
        LOGGER.debug("exitComparisonOperator <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterLogicalOperator(MySqlParser.LogicalOperatorContext ctx) {
        LOGGER.debug("enterLogicalOperator <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitLogicalOperator(MySqlParser.LogicalOperatorContext ctx) {
        LOGGER.debug("exitLogicalOperator <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterBitOperator(MySqlParser.BitOperatorContext ctx) {
        LOGGER.debug("enterBitOperator <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitBitOperator(MySqlParser.BitOperatorContext ctx) {
        LOGGER.debug("exitBitOperator <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterMathOperator(MySqlParser.MathOperatorContext ctx) {
        LOGGER.debug("enterMathOperator <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitMathOperator(MySqlParser.MathOperatorContext ctx) {
        LOGGER.debug("exitMathOperator <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterJsonOperator(MySqlParser.JsonOperatorContext ctx) {
        LOGGER.debug("enterJsonOperator <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitJsonOperator(MySqlParser.JsonOperatorContext ctx) {
        LOGGER.debug("exitJsonOperator <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterCharsetNameBase(MySqlParser.CharsetNameBaseContext ctx) {
        LOGGER.debug("enterCharsetNameBase <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitCharsetNameBase(MySqlParser.CharsetNameBaseContext ctx) {
        LOGGER.debug("exitCharsetNameBase <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterTransactionLevelBase(MySqlParser.TransactionLevelBaseContext ctx) {
        LOGGER.debug("enterTransactionLevelBase <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitTransactionLevelBase(MySqlParser.TransactionLevelBaseContext ctx) {
        LOGGER.debug("exitTransactionLevelBase <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterPrivilegesBase(MySqlParser.PrivilegesBaseContext ctx) {
        LOGGER.debug("enterPrivilegesBase <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitPrivilegesBase(MySqlParser.PrivilegesBaseContext ctx) {
        LOGGER.debug("exitPrivilegesBase <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterIntervalTypeBase(MySqlParser.IntervalTypeBaseContext ctx) {
        LOGGER.debug("enterIntervalTypeBase <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitIntervalTypeBase(MySqlParser.IntervalTypeBaseContext ctx) {
        LOGGER.debug("exitIntervalTypeBase <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterDataTypeBase(MySqlParser.DataTypeBaseContext ctx) {
        LOGGER.debug("enterDataTypeBase <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitDataTypeBase(MySqlParser.DataTypeBaseContext ctx) {
        LOGGER.debug("exitDataTypeBase <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterKeywordsCanBeId(MySqlParser.KeywordsCanBeIdContext ctx) {
        LOGGER.debug("enterKeywordsCanBeId <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitKeywordsCanBeId(MySqlParser.KeywordsCanBeIdContext ctx) {
        LOGGER.debug("exitKeywordsCanBeId <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterFunctionNameBase(MySqlParser.FunctionNameBaseContext ctx) {
        LOGGER.debug("enterFunctionNameBase <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitFunctionNameBase(MySqlParser.FunctionNameBaseContext ctx) {
        LOGGER.debug("exitFunctionNameBase <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void enterEveryRule(ParserRuleContext ctx) {
        LOGGER.debug("enterEveryRule <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void exitEveryRule(ParserRuleContext ctx) {
        LOGGER.debug("exitEveryRule <- {}", ctx.toStringTree(parser));
    }

    @Override
    public void visitTerminal(TerminalNode node) {
        LOGGER.debug("visitTerminal <- {}", node.toStringTree(parser));
    }

    @Override
    public void visitErrorNode(ErrorNode node) {
        LOGGER.debug("visitErrorNode <- {}", node.toStringTree(parser));
    }
}
