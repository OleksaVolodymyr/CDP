package com.epam.lab.utils.logging;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class TestLog implements ITestLog {

    private String message;
    private String level;
    private String threadName;
    private LocalDateTime date;
    private String className;
    private int lineNumber;
    private String methodName;


    public String getMessage() {
        return message;
    }

    public TestLog setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getLevel() {
        return level;
    }

    public TestLog setLevel(String level) {
        this.level = level;
        return this;
    }

    public String getThreadName() {
        return threadName;
    }

    public TestLog setThreadName(String threadName) {
        this.threadName = threadName;
        return this;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public TestLog setDate(long date) {
        this.date = LocalDateTime.ofInstant(Instant.ofEpochMilli(date), ZoneId.systemDefault());
        return this;
    }

    public String getClassName() {
        return className;
    }

    public TestLog setClassName(String className) {
        this.className = className;
        return this;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public TestLog setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
        return this;
    }

    public String getMethodName() {
        return methodName;
    }

    public TestLog setMethodName(String methodName) {
        this.methodName = methodName;
        return this;
    }

    @Override
    public TestLog build() {
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestLog testLog = (TestLog) o;
        return lineNumber == testLog.lineNumber &&
                Objects.equals(message, testLog.message) &&
                Objects.equals(level, testLog.level) &&
                Objects.equals(threadName, testLog.threadName) &&
                Objects.equals(date, testLog.date) &&
                Objects.equals(className, testLog.className) &&
                Objects.equals(methodName, testLog.methodName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, level, threadName, date, className, lineNumber, methodName);
    }

    @Override
    public String toString() {
        return "TestLog{" +
                "Date=" + formatDate() +
                ", level='" + level + '\'' +
                ", threadName='" + threadName + '\'' +
                ", className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", lineNumber=" + lineNumber +
                ", message='" + message + '\'' +
                '}';
    }

    private String formatDate() {
        return date.format(DateTimeFormatter.ofPattern("dd/MMM/yyyy HH:mm:ss"));
    }
}
