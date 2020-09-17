package com.azure.autorest.postprocessor.ls.models;

public class TextDocumentSync
{
    private boolean openClose;

    private int change;

    private Save save;

    private boolean willSave;

    public void setOpenClose(boolean openClose){
        this.openClose = openClose;
    }
    public boolean isOpenClose(){
        return this.openClose;
    }
    public void setChange(int change){
        this.change = change;
    }
    public int getChange(){
        return this.change;
    }
    public void setSave(Save save){
        this.save = save;
    }
    public Save getSave(){
        return this.save;
    }
    public boolean isWillSave() {
        return willSave;
    }
    public void setWillSave(boolean willSave) {
        this.willSave = willSave;
    }
}
