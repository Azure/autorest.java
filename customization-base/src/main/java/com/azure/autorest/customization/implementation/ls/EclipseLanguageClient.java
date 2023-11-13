// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.customization.implementation.ls;

import com.azure.autorest.customization.implementation.ls.models.JavaCodeActionKind;
import com.azure.autorest.extension.base.jsonrpc.Connection;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.eclipse.lsp4j.ClientCapabilities;
import org.eclipse.lsp4j.CodeAction;
import org.eclipse.lsp4j.CodeActionCapabilities;
import org.eclipse.lsp4j.CodeActionContext;
import org.eclipse.lsp4j.CodeActionKind;
import org.eclipse.lsp4j.CodeActionKindCapabilities;
import org.eclipse.lsp4j.CodeActionLiteralSupportCapabilities;
import org.eclipse.lsp4j.CodeActionParams;
import org.eclipse.lsp4j.CodeActionResolveSupportCapabilities;
import org.eclipse.lsp4j.DidChangeWatchedFilesParams;
import org.eclipse.lsp4j.DocumentFormattingParams;
import org.eclipse.lsp4j.DocumentSymbolParams;
import org.eclipse.lsp4j.FileEvent;
import org.eclipse.lsp4j.FormattingOptions;
import org.eclipse.lsp4j.InitializeParams;
import org.eclipse.lsp4j.InitializeResult;
import org.eclipse.lsp4j.Position;
import org.eclipse.lsp4j.Range;
import org.eclipse.lsp4j.RenameParams;
import org.eclipse.lsp4j.ServerCapabilities;
import org.eclipse.lsp4j.SymbolCapabilities;
import org.eclipse.lsp4j.SymbolInformation;
import org.eclipse.lsp4j.SymbolKind;
import org.eclipse.lsp4j.SymbolKindCapabilities;
import org.eclipse.lsp4j.TextDocumentClientCapabilities;
import org.eclipse.lsp4j.TextDocumentIdentifier;
import org.eclipse.lsp4j.TextEdit;
import org.eclipse.lsp4j.WorkspaceClientCapabilities;
import org.eclipse.lsp4j.WorkspaceEdit;
import org.eclipse.lsp4j.WorkspaceFolder;
import org.eclipse.lsp4j.WorkspaceSymbolParams;
import org.eclipse.lsp4j.jsonrpc.json.MessageJsonHandler;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class EclipseLanguageClient implements AutoCloseable {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final Gson GSON = new MessageJsonHandler(null).getDefaultGsonBuilder().create();

    private static final Type LIST_TEXT_EDIT = createParameterizedType(List.class, TextEdit.class);
    private static final Type LIST_SYMBOL_INFORMATION = createParameterizedType(List.class, SymbolInformation.class);
    private static final Type LIST_CODE_ACTION = createParameterizedType(List.class, CodeAction.class);

    private final EclipseLanguageServerFacade server;
    private final Connection connection;
    private final ServerSocket serverSocket;
    private final AtomicReference<Socket> clientSocket = new AtomicReference<>();
    private final String workspaceDir;
    private ServerCapabilities serverCapabilities;

    public EclipseLanguageClient(String workspaceDir) {
        this(null, workspaceDir);
    }

    public EclipseLanguageClient(String pathToLanguageServerPlugin, String workspaceDir) {
        this.workspaceDir = new File(workspaceDir).toURI().toString();
        try {
            serverSocket = new ServerSocket(0);
            int port = serverSocket.getLocalPort();
            Thread thread = new Thread(() -> {
                try {
                    clientSocket.set(serverSocket.accept());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            thread.start();
            Thread.sleep(1000);
            if (pathToLanguageServerPlugin == null) {
                this.server = new EclipseLanguageServerFacade(port);
            } else {
                this.server = new EclipseLanguageServerFacade(pathToLanguageServerPlugin, port);
            }
            thread.join(10 * 1000);
            if (clientSocket.get() == null) {
                throw new IllegalStateException("EclipseLanguageServer failed to start on CLIENT_PORT " + port + ". "
                        + "Make sure you have stopped any previous EclipseLanguageServer. "
                        + "If not sure, you may kill all 'java' process.");
            }
            connection = new Connection(clientSocket.get().getOutputStream(), clientSocket.get().getInputStream());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void initialize() {
        int pid = (int) ProcessHandle.current().pid();

        InitializeParams initializeParams = new InitializeParams();
        initializeParams.setProcessId(pid);
        initializeParams.setRootUri(workspaceDir);
        initializeParams.setWorkspaceFolders(new ArrayList<>());
        WorkspaceFolder workspaceFolder = new WorkspaceFolder();
        workspaceFolder.setName("root");
        workspaceFolder.setUri(workspaceDir);
        initializeParams.getWorkspaceFolders().add(workspaceFolder);
        initializeParams.setTrace("message");
        initializeParams.setCapabilities(new ClientCapabilities());
        initializeParams.getCapabilities().setWorkspace(new WorkspaceClientCapabilities());
        initializeParams.getCapabilities().getWorkspace().setSymbol(new SymbolCapabilities(false));
        initializeParams.getCapabilities().getWorkspace().getSymbol().setSymbolKind(new SymbolKindCapabilities());
        initializeParams.getCapabilities().getWorkspace().getSymbol().getSymbolKind().setValueSet(Arrays.asList(SymbolKind.values()));
        initializeParams.getCapabilities().getWorkspace().setWorkspaceFolders(true);
        initializeParams.getCapabilities().setTextDocument(new TextDocumentClientCapabilities());
        initializeParams.getCapabilities().getTextDocument().setCodeAction(new CodeActionCapabilities(false));
        initializeParams.getCapabilities().getTextDocument().getCodeAction().setResolveSupport(new CodeActionResolveSupportCapabilities(Collections.emptyList()));
        initializeParams.getCapabilities().getTextDocument().getCodeAction().setCodeActionLiteralSupport(new CodeActionLiteralSupportCapabilities());
        initializeParams.getCapabilities().getTextDocument().getCodeAction().getCodeActionLiteralSupport().setCodeActionKind(new CodeActionKindCapabilities());
        initializeParams.getCapabilities().getTextDocument().getCodeAction().getCodeActionLiteralSupport().getCodeActionKind().setValueSet(new ArrayList<>());

        initializeParams.getCapabilities().getTextDocument().getCodeAction().getCodeActionLiteralSupport().getCodeActionKind().getValueSet().add(CodeActionKind.QuickFix);
        initializeParams.getCapabilities().getTextDocument().getCodeAction().getCodeActionLiteralSupport().getCodeActionKind().getValueSet().add(CodeActionKind.Refactor);
        initializeParams.getCapabilities().getTextDocument().getCodeAction().getCodeActionLiteralSupport().getCodeActionKind().getValueSet().add(CodeActionKind.RefactorExtract);
        initializeParams.getCapabilities().getTextDocument().getCodeAction().getCodeActionLiteralSupport().getCodeActionKind().getValueSet().add(CodeActionKind.RefactorInline);
        initializeParams.getCapabilities().getTextDocument().getCodeAction().getCodeActionLiteralSupport().getCodeActionKind().getValueSet().add(CodeActionKind.RefactorRewrite);
        initializeParams.getCapabilities().getTextDocument().getCodeAction().getCodeActionLiteralSupport().getCodeActionKind().getValueSet().add(CodeActionKind.Source);
        initializeParams.getCapabilities().getTextDocument().getCodeAction().getCodeActionLiteralSupport().getCodeActionKind().getValueSet().add(CodeActionKind.SourceOrganizeImports);

        for (JavaCodeActionKind javaCodeActionKind : JavaCodeActionKind.values()) {
            initializeParams.getCapabilities().getTextDocument().getCodeAction().getCodeActionLiteralSupport().getCodeActionKind().getValueSet().add(javaCodeActionKind.toString());
        }

        InitializeResult initializeResult = sendRequest(connection, "initialize", initializeParams, InitializeResult.class);
        this.serverCapabilities = initializeResult.getCapabilities();
        connection.notifyWithObject("initialized", null);
        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public List<TextEdit> format(String fileUri) {
        if (serverCapabilities.getDocumentFormattingProvider().getLeft()) {
            DocumentFormattingParams params = new DocumentFormattingParams();
            params.setTextDocument(new TextDocumentIdentifier(fileUri));
            params.setOptions(new FormattingOptions());
            params.getOptions().setTabSize(4);
            params.getOptions().setInsertSpaces(true);
            params.getOptions().setTrimTrailingWhitespace(true);

            return sendRequest(connection, "textDocument/formatting", params, LIST_TEXT_EDIT);
        } else {
            return Collections.emptyList();
        }
    }

    public void notifyWatchedFilesChanged(List<FileEvent> changes) {
        DidChangeWatchedFilesParams params = new DidChangeWatchedFilesParams(changes);
        connection.notifyWithSerializedObject("workspace/didChangeWatchedFiles", GSON.toJson(params));
    }

    public List<SymbolInformation> findWorkspaceSymbol(String query) {
        WorkspaceSymbolParams workspaceSymbolParams = new WorkspaceSymbolParams();
        workspaceSymbolParams.setQuery(query);

        return sendRequest(connection, "workspace/symbol", workspaceSymbolParams, LIST_SYMBOL_INFORMATION);
    }

    public List<SymbolInformation> listDocumentSymbols(String fileUri) {
        DocumentSymbolParams documentSymbolParams = new DocumentSymbolParams();
        documentSymbolParams.setTextDocument(new TextDocumentIdentifier(fileUri));

        return sendRequest(connection, "textDocument/documentSymbol", documentSymbolParams, LIST_SYMBOL_INFORMATION);
    }

    public WorkspaceEdit renameSymbol(String fileUri, Position symbolPosition, String newName) {
        RenameParams renameParams = new RenameParams();
        renameParams.setTextDocument(new TextDocumentIdentifier(fileUri));
        renameParams.setPosition(symbolPosition);
        renameParams.setNewName(newName);

        return sendRequest(connection, "textDocument/rename", renameParams, WorkspaceEdit.class);
    }

    public List<CodeAction> listCodeActions(String fileUri, Range range, String codeActionKind) {
        CodeActionParams codeActionParams = new CodeActionParams();
        codeActionParams.setTextDocument(new TextDocumentIdentifier(fileUri));
        codeActionParams.setRange(range);
        CodeActionContext context = new CodeActionContext(Collections.emptyList());
        context.setOnly(Collections.singletonList(codeActionKind));
        codeActionParams.setContext(context);

        List<CodeAction> codeActions = sendRequest(connection, "textDocument/codeAction", codeActionParams, LIST_CODE_ACTION);
        for (CodeAction codeAction : codeActions) {
            if (codeAction.getEdit() != null) {
                continue;
            }

            if ("java.apply.workspaceEdit".equals(codeAction.getCommand().getCommand())) {
                codeAction.setEdit(GSON.fromJson((JsonObject) codeAction.getCommand().getArguments().get(0),
                    WorkspaceEdit.class));
            }
        }

        return codeActions;
    }

    public void close() {
        try {
            connection.notifyWithObject("exit", null);
            clientSocket.get().close();
            serverSocket.close();
            connection.stop();
            server.shutdown();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static <T> T sendRequest(Connection connection, String method, Object param, Type responseType) {
        try {
            String response = OBJECT_MAPPER.writeValueAsString(connection.requestWithSerializedObject(
                OBJECT_MAPPER.constructType(JsonNode.class), method, GSON.toJson(param)));

            return GSON.fromJson(response, responseType);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private static Type createParameterizedType(Type rawType, Type... typeArguments) {
        return new ParameterizedType() {
            @Override
            public Type[] getActualTypeArguments() {
                return typeArguments;
            }

            @Override
            public Type getRawType() {
                return rawType;
            }

            @Override
            public Type getOwnerType() {
                return null;
            }
        };
    }
}
