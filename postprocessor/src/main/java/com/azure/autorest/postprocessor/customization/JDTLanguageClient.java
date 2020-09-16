package com.azure.autorest.postprocessor.customization;

import com.azure.autorest.extension.base.jsonrpc.Connection;
import com.azure.autorest.postprocessor.ls.models.ClientCapabilities;
import com.azure.autorest.postprocessor.ls.models.InitializeParams;
import com.azure.autorest.postprocessor.ls.models.InitializeResponse;
import com.azure.autorest.postprocessor.ls.models.Position;
import com.azure.autorest.postprocessor.ls.models.RenameParams;
import com.azure.autorest.postprocessor.ls.models.SymbolInformation;
import com.azure.autorest.postprocessor.ls.models.SymbolKind;
import com.azure.autorest.postprocessor.ls.models.SymbolKindCapabilities;
import com.azure.autorest.postprocessor.ls.models.TextDocumentIdentifier;
import com.azure.autorest.postprocessor.ls.models.WorkspaceCapabilities;
import com.azure.autorest.postprocessor.ls.models.WorkspaceEdit;
import com.azure.autorest.postprocessor.ls.models.WorkspaceFolder;
import com.azure.autorest.postprocessor.ls.models.WorkspaceSymbolClientCapabilities;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class JDTLanguageClient {
    private final JDTLanguageServer server;
    private final Connection connection;
    private final ServerSocket serverSocket;
    private final AtomicReference<Socket> clientSocket = new AtomicReference<>();
    private final URI workspaceDir;

    public JDTLanguageClient(String workspaceDir) {
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
            this.server = new JDTLanguageServer(workspaceDir, port);
            thread.join();
            connection = new Connection(clientSocket.get().getOutputStream(), clientSocket.get().getInputStream());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public InitializeResponse initialize() {
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
        InitializeResponse response = connection.requestWithObject(new ObjectMapper().constructType(InitializeResponse.class), "initialize", initializeParams);
        connection.notifyWithObject("initialized", null);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    public List<SymbolInformation> findWorkspaceSymbol(String query) {
        Map<String, Object> workspaceSymbolParams = new HashMap<>();
        workspaceSymbolParams.put("query", query);
        return connection.requestWithObject(new ObjectMapper().getTypeFactory().constructCollectionLikeType(List.class, SymbolInformation.class), "workspace/symbol", workspaceSymbolParams);
    }

    public WorkspaceEdit renameSymbol(URI fileUri, Position symbolPosition, String newName) {
        RenameParams renameParams = new RenameParams();
        renameParams.setTextDocument(new TextDocumentIdentifier());
        renameParams.getTextDocument().setUri(fileUri);
        renameParams.setPosition(symbolPosition);
        renameParams.setNewName(newName);
        return connection.requestWithObject(new ObjectMapper().constructType(WorkspaceEdit.class), "textDocument/rename", renameParams);
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
