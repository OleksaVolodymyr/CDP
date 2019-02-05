package com.cdp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Objects;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TestLog implements Serializable, Comparable<TestLog> {

    private String message;
    private String level;
    private String threadName;
    private String date;
    private String className;
    private int lineNumber;
    private String methodName;

    public TestLog() {
    }

    public TestLog(String message, String level, String threadName, String date, String className, int lineNumber, String methodName) {
        this.message = message;
        this.level = level;
        this.threadName = threadName;
        this.date = date;
        this.className = className;
        this.lineNumber = lineNumber;
        this.methodName = methodName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
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
                "Date=" + date +
                ", level='" + level + '\'' +
                ", threadName='" + threadName + '\'' +
                ", className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", lineNumber=" + lineNumber +
                ", message='" + message + '\'' +
                '}';
    }


    @Override
    public int compareTo(TestLog o) {
        return this.getDate().compareTo(o.getDate());
    }
}
