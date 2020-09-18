package com.azure.autorest.postprocessor;

import com.azure.autorest.postprocessor.ls.models.Position;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Editor {
    private Path rootDir;
    private Map<String, String> contents;
    private Map<String, List<String>> lines;
    private Map<String, Path> paths;

    public Editor(Map<String, String> contents, Path rootDir) {
        this.contents = contents;
        this.lines = new HashMap<>();
        this.paths = new HashMap<>();
        this.rootDir = rootDir;
        for (Map.Entry<String, String> content : contents.entrySet()) {
            addFile(content.getKey(), content.getValue());
        }

    }

    public Map<String, String> getContents() {
        return contents;
    }

    public void addFile(String name, String content) {
        Path newFilePath = Paths.get(rootDir.toString(), name);
        File newFile = newFilePath.toFile();
        if (!newFile.getParentFile().exists()) {
            newFile.getParentFile().mkdirs();
        }
        boolean fileCreated;
        try {
            fileCreated = newFile.createNewFile();
            FileOutputStream stream = new FileOutputStream(newFile);
            stream.write(content.getBytes(StandardCharsets.UTF_8));
            stream.close();
        } catch (IOException e) {
            throw new RuntimeException();
        }

        if (fileCreated) {
            contents.put(name, content);
            lines.put(name, splitContentIntoLines(content));
            paths.put(name, newFilePath);
        }
    }

    public void removeFile(String name) {
        contents.remove(name);
        lines.remove(name);
        paths.get(name).toFile().delete();
        paths.remove(name);
    }

    public String getFileContent(String name) {
        return contents.get(name);
    }

    public List<String> getFileLines(String name) {
        return lines.get(name);

    }

    public List<String> getFileLine(String name) {
        return lines.get(name);
    }

    public void replace(String fileName, Position start, Position end, String newContent) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        List<String> lineContent = lines.get(fileName);
        for (int i = 0; i != start.getLine(); i++) {
            printWriter.println(lineContent.get(i));
        }
        printWriter.print(lineContent.get(start.getLine()).substring(0, start.getCharacter()));
        List<String> replacementLineContent = splitContentIntoLines(newContent);
        for (int i = 0; i != replacementLineContent.size() - 1; i++) {
            printWriter.println(replacementLineContent.get(i));
        }
        printWriter.print(replacementLineContent.get(replacementLineContent.size() - 1));
        printWriter.println(lineContent.get(end.getLine()).substring(end.getCharacter()));
        for (int i = end.getLine() + 1; i != lineContent.size(); i++) {
            printWriter.println(lineContent.get(i));
        }
        contents.put(fileName, stringWriter.toString());
        lines.put(fileName, splitContentIntoLines(contents.get(fileName)));
        try (PrintWriter fileWriter = new PrintWriter(paths.get(fileName).toFile())) {
            fileWriter.print(contents.get(fileName));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void renameFile(String fileName, String newName) {
        contents.put(newName, contents.remove(fileName));
        lines.put(newName, lines.remove(fileName));
        Path path = paths.remove(fileName);
        Path newPath = Paths.get(rootDir.toString(), newName);
        path.toFile().renameTo(newPath.toFile());
    }

    private static List<String> splitContentIntoLines(String content) {
        List<String> res = new ArrayList<>();
        Scanner scanner = new Scanner(content);
        while (scanner.hasNextLine()) {
            res.add(scanner.nextLine());
        }
        return res;
    }

    private static String joinLinesIntoContent(List<String> lines) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        for (String line : lines) {
            printWriter.println(line);
        }
        return stringWriter.toString();
    }
}
