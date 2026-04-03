package Library;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

class LibraryMember {
    String id;
    String name;
    String bookTitle;
    LocalDate checkoutDate;
    LocalDate expectedReturnDate;
    String categoryCode;

    public LibraryMember(String input) {
        String[] parts = input.split(",");
        this.id = parts[0];
        this.name = parts[1];
        this.bookTitle = parts[2];
        this.checkoutDate = LocalDate.parse(parts[3], DateTimeFormatter.ofPattern("yyyyMMdd"));
        this.expectedReturnDate = LocalDate.parse(parts[4], DateTimeFormatter.ofPattern("yyyyMMdd"));
        this.categoryCode = parts[5];
    }

    public void processAndPrint() {
        // 현재 날짜 (평가일 기준: 2026-04-03)
        LocalDate today = LocalDate.of(2026, 4, 3);
        
        // 대출일수 계산
        long overdueDays = ChronoUnit.DAYS.between(checkoutDate, today);
        
        // 대출등급 결정
        String grade;
        if (overdueDays <= 7) {
            grade = "정상";
        } else if (overdueDays <= 14) {
            grade = "주의";
        } else {
            grade = "연체";
        }

        // 회원구분명 결정
        String categoryName;
        switch (categoryCode) {
            case "A": categoryName = "일반회원"; break;
            case "B": categoryName = "우수회원"; break;
            case "C": categoryName = "특별회원"; break;
            default: categoryName = "알수없음";
        }

        System.out.printf("%s,%s,%s,%d,%s,%s\n", id, name, bookTitle, overdueDays, grade, categoryName);
    }
}

public class Library {
    public static void main(String[] args) {
        String[] inputs = {
            "101,최수빈,자바의정석,20260110,20260117,A",
            "102,한지호,스프링입문,20260201,20260208,B",
            "103,오세라,파이썬기초,20260301,20260308,C"
        };

        System.out.println("회원번호,이름,도서명,대출일수,대출등급,회원구분명");
        for (String input : inputs) {
            LibraryMember member = new LibraryMember(input);
            member.processAndPrint();
        }
    }
}
