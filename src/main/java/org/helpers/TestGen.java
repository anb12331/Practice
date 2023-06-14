package org.helpers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TestGen {
    public String generateTestMethod() throws IOException {
        StringBuilder sb = new StringBuilder(25000);
        sb.append("public class TestSolution{\r\n");
        sb.append("public void Iterate(){\r\n");
        sb.append("int i=0;\r\n");
        char[] line = "i++;\r\n".toCharArray();
        for(int i = 0; i < 5000; i++) {
            sb.append(line);
        }
        sb.append("}");
        sb.append("}");
        Path newFile = Path.of("TestSolution.java");
        if(Files.exists(newFile))
            Files.delete(newFile);

        Files.createFile(newFile);

        Files.writeString(newFile, sb.toString());
        return newFile.toString();
    }

    public static void main(String[] args) throws IOException {
        new TestGen().generateTestMethod();
//        new TestSolution().Iterate();
    }

}
