package com.azure.autorest.postprocessor.ls.models;

public class TextDocumentSync
{
    private boolean openClose;

    private int change;

    private Save save;

    public void setOpenClose(boolean openClose){
        this.openClose = openClose;
    }
    public boolean getOpenClose(){
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
}
