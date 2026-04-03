package Movie;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

interface Ipo {
    void input(String input);
    void process();
    void output();
}

class MovieReservation implements Ipo {
    String reservationNo;
    String name;
    String movieCode;
    int visitorCount;
    double baseFee;
    String timeCode;

    String movieName;
    double totalFee;
    double surcharge;
    double finalAmount;
    String timeSlot;

    @Override
    public void input(String input) {
        String[] parts = input.split(",");
        this.reservationNo = parts[0];
        this.name = parts[1];
        this.movieCode = parts[2];
        this.visitorCount = Integer.parseInt(parts[3]);
        this.baseFee = Double.parseDouble(parts[4]);
        this.timeCode = parts[5];
    }

    @Override
    public void process() {
        totalFee = baseFee * visitorCount;

        switch (timeCode) {
            case "M": 
                surcharge = totalFee * 0.0; 
                timeSlot = "조조";
                break;
            case "D": 
                surcharge = totalFee * 0.05; 
                timeSlot = "일반";
                break;
            case "N": 
                surcharge = totalFee * 0.10; 
                timeSlot = "심야";
                break;
            default:
                surcharge = 0;
                timeSlot = "알수없음";
        }

        finalAmount = totalFee + surcharge;

        switch (movieCode) {
            case "A1": movieName = "액션대작"; break;
            case "R1": movieName = "로맨스극장"; break;
            case "C1": movieName = "코미디쇼"; break;
            case "H1": movieName = "공포특집"; break;
            default: movieName = "알수없음";
        }
    }

    @Override
    public void output() {
        System.out.printf("%s,%s,%s,%.2f,%.2f,%.2f,%s\n", 
            reservationNo, name, movieName, totalFee, surcharge, finalAmount, timeSlot);
    }

    public double getFinalAmount() {
        return finalAmount;
    }
}

public class movie {
    public static void main(String[] args) {
        String[] inputs = {
            "T1001,유지호,A1,2,12000.0,M",
            "T1002,한예린,R1,3,14000.0,D",
            "T1003,송민준,H1,1,15000.0,N"
        };

        ArrayList<MovieReservation> list = new ArrayList<>();
        for (String input : inputs) {
            MovieReservation res = new MovieReservation();
            res.input(input);
            res.process();
            list.add(res);
        }

        // 최종결제금액 기준 내림차순 정렬
        Collections.sort(list, new Comparator<MovieReservation>() {
            @Override
            public int compare(MovieReservation o1, MovieReservation o2) {
                return Double.compare(o2.getFinalAmount(), o1.getFinalAmount());
            }
        });

        System.out.println("예매번호,이름,영화명,총요금,할증액,최종결제금액,시간대명");
        for (MovieReservation res : list) {
            res.output();
        }
    }
}
