package com.kokerwang.pubdialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple package looked comfortable on List<DialogObject>
 * <p/>
 * Created by KokerWang on 15/2/26.
 */
public class DialogGroup {
    private List<DialogObject> dialogObjects;

    public DialogGroup() {
        dialogObjects = new ArrayList<>();
    }

    public DialogGroup(int size) {
        dialogObjects = new ArrayList<>(size);
    }

    public DialogGroup(String dialogObjectName) {
        dialogObjects = new ArrayList<>();
        dialogObjects.add(new DialogObject(dialogObjectName));
    }

    public DialogGroup(DialogObject dialogObject) {
        dialogObjects = new ArrayList<>();
        dialogObjects.add(dialogObject);
    }

    public DialogGroup(DialogObject dialogObject, int size) {
        dialogObjects = new ArrayList<>(size);
        dialogObjects.add(dialogObject);
    }

    public DialogGroup(List<String> strings) {
        dialogObjects = new ArrayList<>(strings.size());
        for (String str : strings) {
            dialogObjects.add(new DialogObject(str));
        }
    }

    public int size() {
        return dialogObjects.size();
    }

    public DialogObject get(int location) {
        return dialogObjects.get(location);
    }

    public boolean add(DialogObject dialogObject) {
        return dialogObjects.add(dialogObject);
    }

    public void add(int location, DialogObject dialogObject) {
        dialogObjects.add(location, dialogObject);
    }

    public List<DialogObject> getDialogObjects() {
        return dialogObjects;
    }
}
