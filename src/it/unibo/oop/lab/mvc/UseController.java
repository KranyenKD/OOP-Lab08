package it.unibo.oop.lab.mvc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class StringEmpty extends IllegalStateException{
    String msg;
    public StringEmpty(){
        super();
        msg = "You tried to insert a empty string!";
    }
    private String getMsg() {
        return msg;
    }
    public String toString() {
        return this.getMsg();
    }
}

public class UseController implements Controller{
    String nextString;
    private List<String> history = new ArrayList<>();;
    
    
    @Override
    public void setNextString(String  str){
        if(!str.isEmpty()) {
            nextString = str;
        }
        else {
            throw  new StringEmpty();
        }
    }

    @Override
    public String getNextString() {
        
        return nextString;
    }

    @Override
    public List<String> getHistory() {
        // TODO Auto-generated method stub
        return history;
    }

    @Override
    public void printString() {
        String str;
        str = this.getNextString();
        if(str != null) {
            System.out.println(str);
            history.add(str);
        }
        else {
            throw new IllegalStateException();
        }
        
    }

}
