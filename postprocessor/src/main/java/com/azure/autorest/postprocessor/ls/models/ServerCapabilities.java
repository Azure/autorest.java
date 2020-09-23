package com.azure.autorest.postprocessor.ls.models;

public class ServerCapabilities
{
    private TextDocumentSync textDocumentSync;

    private boolean hoverProvider;

    private CompletionProvider completionProvider;

    private SignatureHelpProvider signatureHelpProvider;

    private boolean definitionProvider;

    private boolean typeDefinitionProvider;

    private boolean implementationProvider;

    private boolean referencesProvider;

    private boolean documentHighlightProvider;

    private boolean documentSymbolProvider;

    private boolean workspaceSymbolProvider;

    private boolean codeActionProvider;

    private CodeLensProvider codeLensProvider;

    private boolean documentFormattingProvider;

    private boolean documentRangeFormattingProvider;

    private DocumentOnTypeFormattingProvider documentOnTypeFormattingProvider;

    private RenameProvider renameProvider;

    private boolean foldingRangeProvider;

    private ExecuteCommandProvider executeCommandProvider;

    private Workspace workspace;

    private boolean callHierarchyProvider;

    private boolean selectionRangeProvider;

    public void setTextDocumentSync(TextDocumentSync textDocumentSync){
        this.textDocumentSync = textDocumentSync;
    }
    public TextDocumentSync getTextDocumentSync(){
        return this.textDocumentSync;
    }
    public void setHoverProvider(boolean hoverProvider){
        this.hoverProvider = hoverProvider;
    }
    public boolean getHoverProvider(){
        return this.hoverProvider;
    }
    public void setCompletionProvider(CompletionProvider completionProvider){
        this.completionProvider = completionProvider;
    }
    public CompletionProvider getCompletionProvider(){
        return this.completionProvider;
    }
    public void setSignatureHelpProvider(SignatureHelpProvider signatureHelpProvider){
        this.signatureHelpProvider = signatureHelpProvider;
    }
    public SignatureHelpProvider getSignatureHelpProvider(){
        return this.signatureHelpProvider;
    }
    public void setDefinitionProvider(boolean definitionProvider){
        this.definitionProvider = definitionProvider;
    }
    public boolean getDefinitionProvider(){
        return this.definitionProvider;
    }
    public void setTypeDefinitionProvider(boolean typeDefinitionProvider){
        this.typeDefinitionProvider = typeDefinitionProvider;
    }
    public boolean getTypeDefinitionProvider(){
        return this.typeDefinitionProvider;
    }
    public void setImplementationProvider(boolean implementationProvider){
        this.implementationProvider = implementationProvider;
    }
    public boolean getImplementationProvider(){
        return this.implementationProvider;
    }
    public void setReferencesProvider(boolean referencesProvider){
        this.referencesProvider = referencesProvider;
    }
    public boolean getReferencesProvider(){
        return this.referencesProvider;
    }
    public void setDocumentHighlightProvider(boolean documentHighlightProvider){
        this.documentHighlightProvider = documentHighlightProvider;
    }
    public boolean getDocumentHighlightProvider(){
        return this.documentHighlightProvider;
    }
    public void setDocumentSymbolProvider(boolean documentSymbolProvider){
        this.documentSymbolProvider = documentSymbolProvider;
    }
    public boolean getDocumentSymbolProvider(){
        return this.documentSymbolProvider;
    }
    public void setWorkspaceSymbolProvider(boolean workspaceSymbolProvider){
        this.workspaceSymbolProvider = workspaceSymbolProvider;
    }
    public boolean getWorkspaceSymbolProvider(){
        return this.workspaceSymbolProvider;
    }
    public void setCodeActionProvider(boolean codeActionProvider){
        this.codeActionProvider = codeActionProvider;
    }
    public boolean getCodeActionProvider(){
        return this.codeActionProvider;
    }
    public void setCodeLensProvider(CodeLensProvider codeLensProvider){
        this.codeLensProvider = codeLensProvider;
    }
    public CodeLensProvider getCodeLensProvider(){
        return this.codeLensProvider;
    }
    public void setDocumentFormattingProvider(boolean documentFormattingProvider){
        this.documentFormattingProvider = documentFormattingProvider;
    }
    public boolean getDocumentFormattingProvider(){
        return this.documentFormattingProvider;
    }
    public void setDocumentRangeFormattingProvider(boolean documentRangeFormattingProvider){
        this.documentRangeFormattingProvider = documentRangeFormattingProvider;
    }
    public boolean getDocumentRangeFormattingProvider(){
        return this.documentRangeFormattingProvider;
    }
    public void setDocumentOnTypeFormattingProvider(DocumentOnTypeFormattingProvider documentOnTypeFormattingProvider){
        this.documentOnTypeFormattingProvider = documentOnTypeFormattingProvider;
    }
    public DocumentOnTypeFormattingProvider getDocumentOnTypeFormattingProvider(){
        return this.documentOnTypeFormattingProvider;
    }
    public void setRenameProvider(RenameProvider renameProvider){
        this.renameProvider = renameProvider;
    }
    public RenameProvider getRenameProvider(){
        return this.renameProvider;
    }
    public void setFoldingRangeProvider(boolean foldingRangeProvider){
        this.foldingRangeProvider = foldingRangeProvider;
    }
    public boolean getFoldingRangeProvider(){
        return this.foldingRangeProvider;
    }
    public void setExecuteCommandProvider(ExecuteCommandProvider executeCommandProvider){
        this.executeCommandProvider = executeCommandProvider;
    }
    public ExecuteCommandProvider getExecuteCommandProvider(){
        return this.executeCommandProvider;
    }
    public void setWorkspace(Workspace workspace){
        this.workspace = workspace;
    }
    public Workspace getWorkspace(){
        return this.workspace;
    }
    public void setCallHierarchyProvider(boolean callHierarchyProvider){
        this.callHierarchyProvider = callHierarchyProvider;
    }
    public boolean getCallHierarchyProvider(){
        return this.callHierarchyProvider;
    }
    public void setSelectionRangeProvider(boolean selectionRangeProvider){
        this.selectionRangeProvider = selectionRangeProvider;
    }
    public boolean getSelectionRangeProvider(){
        return this.selectionRangeProvider;
    }
}