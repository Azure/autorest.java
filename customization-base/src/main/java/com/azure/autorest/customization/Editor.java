package com.azure.autorest.customization;

import com.azure.autorest.customization.implementation.Utils;
import com.azure.autorest.customization.models.Position;
import com.azure.autorest.customization.models.Range;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.azure.autorest.customization.implementation.Utils.writeLine;

/**
 * The raw editor containing the current files being customized.
 */
public final class Editor {
    private final Path rootDir;
    private final Map<String, String> contents;
    private final Map<String, List<String>> lines;
    private final Map<String, Path> paths;

    /**
     * Creates an editor instance with the file contents and the root directory path.
     *
     * @param contents the map from file relative paths (starting with "src/main/java") and file contents
     * @param rootDir the root directory path containing the files
     */
    public Editor(Map<String, String> contents, Path rootDir) {
        this.contents = contents;
        this.lines = new HashMap<>();
        this.paths = new HashMap<>();
        this.rootDir = rootDir;
        for (Map.Entry<String, String> content : contents.entrySet()) {
            addFile(content.getKey(), content.getValue());
        }

    }

    /**
     * Gets the mapping from file relative paths (starting with "src/main/java") to file contents.
     *
     * @return the mapping
     */
    public Map<String, String> getContents() {
        return contents;
    }

    /**
     * Adds a new file.
     *
     * @param name the relative path of the file, starting with "src/main/java"
     * @param content the file content
     */
    public void addFile(String name, String content) {
        Path newFilePath = Paths.get(rootDir.toString(), name);
        File newFile = newFilePath.toFile();
        if (!newFile.getParentFile().exists()) {
            newFile.getParentFile().mkdirs();
        }
        boolean fileCreated;
        try {
            fileCreated = newFile.createNewFile();
            BufferedWriter writer = Files.newBufferedWriter(newFile.toPath());
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException();
        }

        if (fileCreated) {
            contents.put(name, content);
            lines.put(name, splitContentIntoLines(content));
            paths.put(name, newFilePath);
        }
    }

    /**
     * Removes a file.
     *
     * @param name the relative file path, starting with "src/main/java"
     */
    public void removeFile(String name) {
        contents.remove(name);
        lines.remove(name);
        paths.get(name).toFile().delete();
        paths.remove(name);
    }

    /**
     * Gets the content of a file.
     *
     * @param name the relative path of a file, starting with "src/main/java"
     * @return the file content
     */
    public String getFileContent(String name) {
        return contents.get(name);
    }

    /**
     * Gets the file content split into lines.
     *
     * @param name the relative path of a file, starting with "src/main/java"
     * @return the file content split into lines
     */
    public List<String> getFileLines(String name) {
        return lines.get(name);

    }

    /**
     * Gets a line in a file.
     *
     * @param name the relative path of a file, starting with "src/main/java"
     * @param line the line number
     * @return the file content in this line
     */
    public String getFileLine(String name, int line) {
        return lines.get(name).get(line);
    }

    /**
     * Inserts a blank line at a given line number.
     *
     * @param fileName the relative path of a file, starting with "src/main/java"
     * @param line the line number to insert a new line
     * @param indented if the line should be indented at the same level as the next line
     * @return the position of the cursor after indentation (if indented) in this line
     */
    public Position insertBlankLine(String fileName, int line, boolean indented) {
        List<String> lineContent = lines.get(fileName);
        String nextLine = lineContent.get(line);
        String indentation = "";
        if (indented) {
            indentation = nextLine.replaceFirst("[^ ].*$", "");
        }
        lines.get(fileName).add(line, indentation);
        contents.put(fileName, joinLinesIntoContent(lines.get(fileName)));
        return new Position(line, indentation.length());
    }

    /**
     * Replaces a chunk of a text with a new text in the file.
     *
     * @param fileName the relative path of a file, starting with "src/main/java"
     * @param start the starting position to replace, inclusive
     * @param end the ending position to replace, exclusive
     * @param newContent the new content to replace the chunk
     */
    public void replace(String fileName, Position start, Position end, String newContent) {
        StringBuilder stringBuilder = new StringBuilder(4096);
        List<String> lineContent = lines.get(fileName);

        // Copy lines until the start of the change is reached.
        for (int i = 0; i != start.getLine(); i++) {
            writeLine(stringBuilder, lineContent.get(i));
        }

        // Copy until the start of the change.
        stringBuilder.append(lineContent.get(start.getLine()), 0, start.getCharacter());

        List<String> replacementLineContent = splitContentIntoLines(newContent);

        // Add the change.
        if (replacementLineContent.size() > 0) {
            for (int i = 0; i != replacementLineContent.size() - 1; i++) {
                writeLine(stringBuilder, replacementLineContent.get(i));
            }

            stringBuilder.append(replacementLineContent.get(replacementLineContent.size() - 1));
        }

        writeLine(stringBuilder, lineContent.get(end.getLine()).substring(end.getCharacter()));

        // Copy the rest of the file until its end.
        for (int i = end.getLine() + 1; i != lineContent.size(); i++) {
            writeLine(stringBuilder, lineContent.get(i));
        }

        contents.put(fileName, stringBuilder.toString());
        lines.put(fileName, splitContentIntoLines(contents.get(fileName)));
        try (BufferedWriter fileWriter = Files.newBufferedWriter(paths.get(fileName))) {
            fileWriter.write(contents.get(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Renames a file. This simply renames the file, without renaming any class content.
     *
     * @param fileName the original relative path of a file, starting with "src/main/java"
     * @param newName the new relative path of the file, starting with "src/main/java"
     */
    public void renameFile(String fileName, String newName) {
        contents.put(newName, contents.remove(fileName));
        lines.put(newName, lines.remove(fileName));
        Path path = paths.remove(fileName);
        Path newPath = Paths.get(rootDir.toString(), newName);
        path.toFile().renameTo(newPath.toFile());
        paths.put(newName, newPath);
    }

    /**
     * Searches all occurrences of a text in the file.
     *
     * @param fileName the relative path of a file, starting with "src/main/java"
     * @param text the text to search
     * @return the list of ranges containing the occurrences
     */
    public List<Range> searchText(String fileName, String text) {
        return searchTextInternal(fileName, text, false);
    }

    /**
     * Searches the first occurrence of a text in the file.
     *
     * @param fileName the relative path of a file, starting with "src/main/java"
     * @param text the text to search
     * @return the range containing the occurrence
     */
    public Range searchTextFirstOccurrence(String fileName, String text) {
        List<Range> rangeList = searchTextInternal(fileName, text, true);

        return Utils.isNullOrEmpty(rangeList) ? null : rangeList.get(0);
    }

    private List<Range> searchTextInternal(String fileName, String text, boolean findFirst) {
        if (Utils.isNullOrEmpty(lines.get(fileName))) {
            return null;
        }

        List<Range> occurrences = new ArrayList<>();
        Pattern pattern = Pattern.compile(text);
        for (int i = 0; i != lines.get(fileName).size(); i++) {
            Matcher matcher = pattern.matcher(lines.get(fileName).get(i));
            while (matcher.find()) {
                occurrences.add(new Range(new Position(i, matcher.start()), new Position(i, matcher.end())));

                if (findFirst) {
                    return occurrences;
                }
            }
        }

        return occurrences;
    }

    /**
     * Gets the text content in a range in the file. The lines will be joined with an optional delimiter. If the
     * delimiter is null, the lines will be joined with line endings.
     *
     * @param fileName the relative path of a file, starting with "src/main/java"
     * @param range the range to convert to text
     * @param delimiter the optional delimiter described above
     * @return the text in the range
     */
    public String getTextInRange(String fileName, Range range, String delimiter) {
        StringBuilder stringBuilder = new StringBuilder(4096);
        for (int line = range.getStart().getLine(); line <= range.getEnd().getLine(); line++) {
            String lineContent = getFileLine(fileName, line);
            int truncateIndex = 0;
            if (line == range.getStart().getLine()) {
                lineContent = lineContent.substring(range.getStart().getCharacter());
                truncateIndex = range.getStart().getCharacter();
            }
            if (line == range.getEnd().getLine()) {
                lineContent = lineContent.substring(0, range.getEnd().getCharacter() - truncateIndex);
            }
            if (delimiter == null) {
                writeLine(stringBuilder, lineContent);
            } else {
                stringBuilder.append(lineContent).append(delimiter);
            }
        }
        return stringBuilder.toString();
    }

    private static List<String> splitContentIntoLines(String content) {
        List<String> res = new ArrayList<>();
        Scanner scanner = new Scanner(content);
        while (scanner.hasNextLine()) {
            res.add(scanner.nextLine());
        }
        if (content.endsWith("\n")) {
            res.add("");
        }
        return res;
    }

    private static String joinLinesIntoContent(List<String> lines) {
        return String.join(System.lineSeparator(), lines);
    }
}
