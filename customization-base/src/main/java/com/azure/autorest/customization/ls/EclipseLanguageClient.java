package com.azure.autorest.customization.ls;

import com.azure.autorest.extension.base.jsonrpc.Connection;
import com.azure.autorest.customization.ls.models.ClientCapabilities;
import com.azure.autorest.customization.ls.models.CodeAction;
import com.azure.autorest.customization.ls.models.CodeActionClientCapabilities;
import com.azure.autorest.customization.ls.models.CodeActionKind;
import com.azure.autorest.customization.ls.models.CodeActionKindValueSet;
import com.azure.autorest.customization.ls.models.CodeActionLiteralSupport;
import com.azure.autorest.customization.ls.models.DidChangeTextDocumentParams;
import com.azure.autorest.customization.ls.models.DidChangeWatchedFilesParams;
import com.azure.autorest.customization.ls.models.DidCloseTextDocumentParams;
import com.azure.autorest.customization.ls.models.DidOpenTextDocumentParams;
import com.azure.autorest.customization.ls.models.DidSaveTextDocumentParams;
import com.azure.autorest.customization.ls.models.DocumentFormattingParams;
import com.azure.autorest.customization.ls.models.DocumentSymbolParams;
import com.azure.autorest.customization.ls.models.FileEvent;
import com.azure.autorest.customization.ls.models.FormattingOptions;
import com.azure.autorest.customization.ls.models.InitializeParams;
import com.azure.autorest.customization.ls.models.InitializeResponse;
import com.azure.autorest.customization.ls.models.JavaCodeActionKind;
import com.azure.autorest.customization.ls.models.Position;
import com.azure.autorest.customization.ls.models.Range;
import com.azure.autorest.customization.ls.models.RenameParams;
import com.azure.autorest.customization.ls.models.ServerCapabilities;
import com.azure.autorest.customization.ls.models.SymbolInformation;
import com.azure.autorest.customization.ls.models.SymbolKind;
import com.azure.autorest.customization.ls.models.SymbolKindCapabilities;
import com.azure.autorest.customization.ls.models.TextDocumentClientCapabilities;
import com.azure.autorest.customization.ls.models.TextDocumentContentChangeEvent;
import com.azure.autorest.customization.ls.models.TextDocumentIdentifier;
import com.azure.autorest.customization.ls.models.TextDocumentItem;
import com.azure.autorest.customization.ls.models.TextEdit;
import com.azure.autorest.customization.ls.models.VersionedTextDocumentIdentifier;
import com.azure.autorest.customization.ls.models.WillSaveTextDocumentParams;
import com.azure.autorest.customization.ls.models.WorkspaceCapabilities;
import com.azure.autorest.customization.ls.models.WorkspaceEdit;
import com.azure.autorest.customization.ls.models.WorkspaceFolder;
import com.azure.autorest.customization.ls.models.WorkspaceSymbolClientCapabilities;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;
import com.sun.jna.platform.win32.Kernel32;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class EclipseLanguageClient {
    private final EclipseLanguageServerFacade server;
    private final Connection connection;
    private final ServerSocket serverSocket;
    private final AtomicReference<Socket> clientSocket = new AtomicReference<>();
    private final URI workspaceDir;
    private ServerCapabilities serverCapabilities;

    public EclipseLanguageClient(String workspaceDir) {
        this.workspaceDir = new File(workspaceDir).toURI();
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
            this.server = new EclipseLanguageServerFacade(workspaceDir, port);
            thread.join(10000);
            connection = new Connection(clientSocket.get().getOutputStream(), clientSocket.get().getInputStream());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void initialize() {
        int pid;
        if (Platform.isWindows()) {
            pid = Kernel32.INSTANCE.GetCurrentProcessId();
        } else {
            pid = CLibrary.INSTANCE.getpid();
        }
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
        initializeParams.getCapabilities().setWorkspace(new WorkspaceCapabilities());
        initializeParams.getCapabilities().getWorkspace().setSymbol(new WorkspaceSymbolClientCapabilities());
        initializeParams.getCapabilities().getWorkspace().getSymbol().setSymbolKind(new SymbolKindCapabilities());
        initializeParams.getCapabilities().getWorkspace().getSymbol().getSymbolKind().setValueSet(Arrays.asList(SymbolKind.values()));
        initializeParams.getCapabilities().getWorkspace().getSymbol().setDynamicRegistration(false);
        initializeParams.getCapabilities().getWorkspace().setWorkspaceFolders(true);
        initializeParams.getCapabilities().setTextDocument(new TextDocumentClientCapabilities());
        initializeParams.getCapabilities().getTextDocument().setCodeAction(new CodeActionClientCapabilities());
        initializeParams.getCapabilities().getTextDocument().getCodeAction().setCodeActionLiteralSupport(new CodeActionLiteralSupport());
        initializeParams.getCapabilities().getTextDocument().getCodeAction().getCodeActionLiteralSupport().setCodeActionKind(new CodeActionKindValueSet());
        initializeParams.getCapabilities().getTextDocument().getCodeAction().getCodeActionLiteralSupport().getCodeActionKind().setValueSet(new ArrayList<>());
        for (CodeActionKind kind : CodeActionKind.values()) {
            initializeParams.getCapabilities().getTextDocument().getCodeAction().getCodeActionLiteralSupport().getCodeActionKind().getValueSet().add(kind.toString());
        }
        for (JavaCodeActionKind kind : JavaCodeActionKind.values()) {
            initializeParams.getCapabilities().getTextDocument().getCodeAction().getCodeActionLiteralSupport().getCodeActionKind().getValueSet().add(kind.toString());
        }
        InitializeResponse response = connection.requestWithObject(new ObjectMapper().constructType(InitializeResponse.class), "initialize", initializeParams);
        serverCapabilities = response.getCapabilities();
        connection.notifyWithObject("initialized", null);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public List<TextEdit> format(URI fileUri) {
        if (serverCapabilities.getDocumentFormattingProvider()) {
            DocumentFormattingParams params = new DocumentFormattingParams();
            params.setTextDocument(new TextDocumentIdentifier(fileUri));
            params.setOptions(new FormattingOptions());
            params.getOptions().setTabSize(4);
            params.getOptions().setInsertSpaces(true);
            params.getOptions().setTrimTrailingWhitespace(true);
            return connection.requestWithObject(new ObjectMapper().getTypeFactory().constructCollectionLikeType(List.class, TextEdit.class), "textDocument/formatting", params);
        } else {
            return Collections.emptyList();
        }
    }

    public void notifyFileOpened(URI fileUri, String content, int version) {
        if (serverCapabilities.getTextDocumentSync() != null
                && serverCapabilities.getTextDocumentSync().isOpenClose()) {
            DidOpenTextDocumentParams params = new DidOpenTextDocumentParams();
            TextDocumentItem item = new TextDocumentItem();
            item.setUri(fileUri);
            item.setText(content);
            item.setVersion(version);
            params.setTextDocument(item);
            connection.notifyWithObject("textDocument/didOpen", params);
        }
    }

    public void notifyFileClosed(URI fileUri) {
        if (serverCapabilities.getTextDocumentSync() != null
                && serverCapabilities.getTextDocumentSync().isOpenClose()) {
            DidCloseTextDocumentParams params = new DidCloseTextDocumentParams();
            TextDocumentIdentifier item = new TextDocumentIdentifier(fileUri);
            params.setTextDocument(item);
            connection.notifyWithObject("textDocument/didClose", params);
        }
    }

    public void notifyFileChanged(URI fileUri, String newContent, List<TextEdit> textEdits, int version) {
        if (serverCapabilities.getTextDocumentSync() != null
                && serverCapabilities.getTextDocumentSync().getChange() != 0) {
            DidChangeTextDocumentParams params = new DidChangeTextDocumentParams();
            VersionedTextDocumentIdentifier item = new VersionedTextDocumentIdentifier(fileUri);
            item.setVersion(version);
            params.setTextDocument(item);
            List<TextDocumentContentChangeEvent> changeEvents = new ArrayList<>();
            for (TextEdit textEdit : textEdits) {
                TextDocumentContentChangeEvent changeEvent = new TextDocumentContentChangeEvent();
                if (serverCapabilities.getTextDocumentSync().getChange() == 1) {
                    changeEvent.setText(newContent);
                } else if (serverCapabilities.getTextDocumentSync().getChange() == 2) {
                    changeEvent.setRange(textEdit.getRange());
                    changeEvent.setText(textEdit.getNewText());
                }
                changeEvents.add(changeEvent);
            }
            params.setContentChanges(changeEvents);
            connection.notifyWithObject("textDocument/didChange", params);
        }
    }

    public void notifyWatchedFilesChanged(List<FileEvent> changes) {
        DidChangeWatchedFilesParams params = new DidChangeWatchedFilesParams();
        params.setChanges(changes);
        connection.notifyWithObject("workspace/didChangeWatchedFiles", params);
    }

    public void notifyFileToSave(URI fileUri) {
        if (serverCapabilities.getTextDocumentSync() != null
                && serverCapabilities.getTextDocumentSync().isWillSave()) {
            WillSaveTextDocumentParams params = new WillSaveTextDocumentParams();
            TextDocumentIdentifier identifier = new TextDocumentIdentifier(fileUri);
            params.setTextDocument(identifier);
            connection.notifyWithObject("textDocument/willSave", params);
        }
    }

    public void notifyFileSaved(URI fileUri, String content) {
        if (serverCapabilities.getTextDocumentSync() != null
                && serverCapabilities.getTextDocumentSync().getSave() != null) {
            DidSaveTextDocumentParams params = new DidSaveTextDocumentParams();
            TextDocumentIdentifier identifier = new TextDocumentIdentifier(fileUri);
            params.setTextDocument(identifier);
            if (serverCapabilities.getTextDocumentSync().getSave().isIncludeText()) {
                params.setText(content);
            }
            connection.notifyWithObject("textDocument/didSave", params);
        }
    }

    public List<SymbolInformation> findWorkspaceSymbol(String query) {
        Map<String, Object> workspaceSymbolParams = new HashMap<>();
        workspaceSymbolParams.put("query", query);
        return connection.requestWithObject(new ObjectMapper().getTypeFactory().constructCollectionLikeType(List.class, SymbolInformation.class), "workspace/symbol", workspaceSymbolParams);
    }

    public List<SymbolInformation> listDocumentSymbols(URI fileUri) {
        DocumentSymbolParams documentSymbolParams = new DocumentSymbolParams();
        documentSymbolParams.setTextDocument(new TextDocumentIdentifier(fileUri));
        return connection.requestWithObject(new ObjectMapper().getTypeFactory().constructCollectionLikeType(List.class, SymbolInformation.class), "textDocument/documentSymbol", documentSymbolParams);
    }

    public WorkspaceEdit renameSymbol(URI fileUri, Position symbolPosition, String newName) {
        RenameParams renameParams = new RenameParams();
        renameParams.setTextDocument(new TextDocumentIdentifier(fileUri));
        renameParams.setPosition(symbolPosition);
        renameParams.setNewName(newName);
        return connection.requestWithObject(new ObjectMapper().constructType(WorkspaceEdit.class), "textDocument/rename", renameParams);
    }

    public List<CodeAction> listCodeActions(URI fileUri, Range range) {
        Map<String, Object> codeActionParams = new HashMap<>();
        codeActionParams.put("textDocument", new TextDocumentIdentifier(fileUri));
        codeActionParams.put("range", range);
        codeActionParams.put("context", Collections.singletonMap("diagnostics", new ArrayList<Object>()));

        return connection.requestWithObject(new ObjectMapper().getTypeFactory().constructCollectionLikeType(List.class, CodeAction.class), "textDocument/codeAction",  codeActionParams);
    }

    public void exit() {
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

    private interface CLibrary extends Library {
        CLibrary INSTANCE = (CLibrary) Native.loadLibrary("c", CLibrary.class);
        int getpid();
    }
}
