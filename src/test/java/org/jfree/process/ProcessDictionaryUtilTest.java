package org.jfree.process;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.ArrayList;
import java.util.List;

/**
 * 方法名加test前缀, 才能被执行.
 */
public class ProcessDictionaryUtilTest extends TestCase {
    private List<ProcessDictionary> processDictionaries;
    
    /**
     * Creates a new test case.
     *
     * @param name  the name.
     */
    public ProcessDictionaryUtilTest(final String name) {
        super(name);
    }
    
    /**
     * Returns a test suite for the JUnit test runner.
     *
     * @return The test suite.
     */
    public static Test suite() {
        return new TestSuite(ProcessDictionaryUtilTest.class);
    }
    
    protected void setUp() {
        processDictionaries = new ArrayList<>();
        beforeGuarantee();
        inGuarantee();
        afterGuarantee();
    }
    
    public void testPrint() {
        for (ProcessDictionary processDictionary : processDictionaries) {
            System.out.println(processDictionary.toString());
        }
        assertTrue(true);
    }
    
    public void testProcessTree() {
        // 组装树结构
        
    }
    
    private void afterGuarantee() {
        ProcessDictionary processDictionary = new ProcessDictionary();
        processDictionary.setId(3L);
        processDictionary.setParentId(0L);
        processDictionary.setDeep(0);
        processDictionary.setProcessKey("");
        processDictionary.setProcessName("保后管理");
        processDictionary.setSort(1003000);
        processDictionary.setStartUrl("");
        processDictionary.setType(12345);
        processDictionaries.add(processDictionary);
    }
    
    private void inGuarantee() {
        ProcessDictionary processDictionary = new ProcessDictionary();
        processDictionary.setId(2L);
        processDictionary.setDeep(0);
        processDictionary.setProcessKey("");
        processDictionary.setProcessName("保中管理");
        processDictionary.setSort(1002000);
        processDictionary.setStartUrl("");
        processDictionary.setType(12346);
        processDictionaries.add(processDictionary);
    
        // 合同制作
        ProcessDictionary processDictionaryApproval = new ProcessDictionary();
        processDictionaryApproval.setId(6L);
        processDictionary.setParentId(2L);
        processDictionaryApproval.setDeep(1);
        processDictionaryApproval.setProcessKey("");
        processDictionaryApproval.setProcessName("合同制作");
        processDictionaryApproval.setSort(1002100);
        processDictionaryApproval.setStartUrl("");
        processDictionary.setType(12346);
        processDictionaries.add(processDictionaryApproval);
    
        // 立项的子集
        for (int i = 30; i < 34; i++) {
            ProcessDictionary processDictionaryApprovalChild = new ProcessDictionary();
            processDictionaryApprovalChild.setId((long)i);
            processDictionary.setParentId(6L);
            processDictionaryApprovalChild.setDeep(2);
            processDictionaryApprovalChild.setProcessKey("InGuarantee" + i);
            processDictionaryApprovalChild.setProcessName("合同制作77" + i);
            processDictionaryApprovalChild.setSort(1002101 + i);
            processDictionaryApproval.setStartUrl("");
            processDictionary.setType(12346);
            processDictionaries.add(processDictionaryApprovalChild);
        }
    
        // 其他
        ProcessDictionary processDictionaryOther = new ProcessDictionary();
        processDictionaryOther.setId(7L);
        processDictionary.setParentId(2L);
        processDictionaryOther.setDeep(1);
        processDictionaryOther.setProcessKey("");
        processDictionaryOther.setProcessName("项目其他");
        processDictionaryOther.setSort(1002200);
        processDictionaryOther.setStartUrl("");
        processDictionary.setType(12346);
        processDictionaries.add(processDictionaryOther);
        // 立项的子集
        for (int i = 40; i < 44; i++) {
            ProcessDictionary processDictionaryApprovalChild = new ProcessDictionary();
            processDictionaryApprovalChild.setId((long)i);
            processDictionary.setParentId(7L);
            processDictionaryApprovalChild.setDeep(2);
            processDictionaryApprovalChild.setProcessKey("other" + i);
            processDictionaryApprovalChild.setProcessName("其他" + i);
            processDictionaryApprovalChild.setSort(1002200 + i);
            processDictionaryApproval.setStartUrl("");
            processDictionary.setType(12346);
            processDictionaries.add(processDictionaryApprovalChild);
        }
    }
    
    private void beforeGuarantee() {
        ProcessDictionary processDictionary = new ProcessDictionary();
        processDictionary.setId(3L);
        processDictionary.setParentId(0L);
        processDictionary.setDeep(0);
        processDictionary.setProcessKey("");
        processDictionary.setProcessName("保前管理");
        processDictionary.setSort(1003000);
        processDictionary.setStartUrl("");
        processDictionary.setType(12347);
        processDictionaries.add(processDictionary);
    
        // 立项
        ProcessDictionary processDictionaryApproval = new ProcessDictionary();
        processDictionaryApproval.setId(4L);
        processDictionary.setParentId(3L);
        processDictionaryApproval.setDeep(1);
        processDictionaryApproval.setProcessKey("");
        processDictionaryApproval.setProcessName("项目立项");
        processDictionaryApproval.setSort(1003100);
        processDictionaryApproval.setStartUrl("");
        processDictionary.setType(12347);
        processDictionaries.add(processDictionaryApproval);
        
        // 立项的子集
        for (int i = 10; i < 14; i++) {
            ProcessDictionary processDictionaryApprovalChild = new ProcessDictionary();
            processDictionaryApprovalChild.setId((long)i);
            processDictionary.setParentId(4L);
            processDictionaryApprovalChild.setDeep(2);
            processDictionaryApprovalChild.setProcessKey("ProjectApply" + i);
            processDictionaryApprovalChild.setProcessName("项目立项" + i);
            processDictionaryApprovalChild.setSort(1003101 + i);
            processDictionaryApproval.setStartUrl("");
            processDictionary.setType(12347);
            processDictionaries.add(processDictionaryApprovalChild);
        }
        
        // 其他
        ProcessDictionary processDictionaryOther = new ProcessDictionary();
        processDictionaryOther.setId(5L);
        processDictionary.setParentId(3L);
        processDictionaryOther.setDeep(1);
        processDictionaryOther.setProcessKey("");
        processDictionaryOther.setProcessName("项目其他");
        processDictionaryOther.setSort(1003150);
        processDictionaryOther.setStartUrl("");
        processDictionary.setType(12347);
        processDictionaries.add(processDictionary);
        // 立项的子集
        for (int i = 20; i < 24; i++) {
            ProcessDictionary processDictionaryApprovalChild = new ProcessDictionary();
            processDictionaryApprovalChild.setId((long)i);
            processDictionary.setParentId(5L);
            processDictionaryApprovalChild.setDeep(2);
            processDictionaryApprovalChild.setProcessKey("other" + i);
            processDictionaryApprovalChild.setProcessName("其他" + i);
            processDictionaryApprovalChild.setSort(1003201 + i);
            processDictionaryApproval.setStartUrl("");
            processDictionary.setType(12347);
            processDictionaries.add(processDictionaryApprovalChild);
        }
    }
}