package com.quietjun.regular;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;


class ReglarexApplicationTests {
    
    @Test
    public void testPatternMatches() {
        String pattern = "[a-z]{2,5}";
        String [] strs = {"henry", "Hello"};
        assertTrue(Pattern.matches(pattern, strs[0]));
        assertFalse(Pattern.matches(pattern, strs[1]));
    }
    
    @Test
    public void useMatcherTest() {
        String pattern = "[a-z]{2,5}";
        String [] strs = {"henry", "Hello"};
        Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(strs[0]);
        assertTrue(m.matches());
        m = p.matcher(strs[1]);
        assertTrue(m.matches());
    }

    @Test
    public void useMatchermatches() {
        String src = "그는 10살이고 1000원이 있다.";
        String regexp = "\\d{1,}";
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(src);
        
        System.out.printf("%s는 %s패턴인가? %b%n", src, regexp, matcher.matches());
        System.out.printf("%s에서 %s패턴을 발견할 수 있나? %b%n", src, regexp, matcher.find());  
    }
    
    @Test
    public void testFind() {
        String src = "그는 10살이고 1000원이 있다.";
        String regexp = "\\d{1,}";
        Matcher m = Pattern.compile(regexp).matcher(src);
        int cnt = 1;
        while(m.find()) {
            int start = m.start();
            int end = m.end()   ;
            System.out.printf("%d번째 일치 (%d~%d) : %s%n",cnt++, start, end, src.substring(start, end) );
        }
    }
    
    @Test
    public void testStream() {
        String src = "그는 10살이고 1000원이 있다.";
        String regexp = "\\d{1,}";
        Matcher m = Pattern.compile(regexp).matcher(src);
        Stream<MatchResult> stream = m.results();
        stream.forEach(mr -> {
            int start = mr.start();
            int end = mr.end()   ;
            System.out.printf("일치 (%d~%d) : %s%n", start, end, src.substring(start, end) );
        });
    }
    
    @Test
    public void testReplace() {
        String src = "그는 10살이고 1000원이 있다.";
        String regexp = "\\d{1,}";
        Matcher m = Pattern.compile(regexp).matcher(src);
        
        String replaced = m.replaceAll("[숫자위치]");
        System.out.printf("전체 변경 결과:%s%n", replaced);

        replaced = m.replaceFirst("[Number Here]");
        System.out.printf("처음만 변경 결과:%s%n", replaced);

        //전체 변경 결과:그는 [숫자위치]살이고 [숫자위치]원이 있다.
        //처음만 변경 결과:그는 [Number Here]살이고 1000원이 있다.
    }
    
    @Test
    public void testStringRegex() {
        String src = "$10,000";
        String regexp = "^\\$[0-9,]{1,}";
        assertTrue(src.matches(regexp));
    }
}
