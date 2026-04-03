package Apt;
import java.util.ArrayList;

class ApartmentData {
    String unit;
    String name;
    int electricityUsage;
    int waterUsage;
    int baseFee;
    String pyeongCode;

    public ApartmentData(String input) {
        String[] parts = input.split(",");
        this.unit = parts[0];
        this.name = parts[1];
        this.electricityUsage = Integer.parseInt(parts[2]);
        this.waterUsage = Integer.parseInt(parts[3]);
        this.baseFee = Integer.parseInt(parts[4]);
        this.pyeongCode = parts[5];
    }

    public void process() {
        double electricityFee = electricityUsage * 120.0;
        double waterFee = waterUsage * 900.0;
        double totalFee = baseFee + electricityFee + waterFee;

        String pyeongName;
        switch (pyeongCode) {
            case "A": pyeongName = "20평형"; break;
            case "B": pyeongName = "30평형"; break;
            case "C": pyeongName = "40평형"; break;
            case "D": pyeongName = "50평형"; break;
            default: pyeongName = "알수없음";
        }

        System.out.printf("%s,%s,%.2f,%.2f,%.2f,%s\n", unit, name, electricityFee, waterFee, totalFee, pyeongName);
    }
}

public class apt {
    public static void main(String[] args) {
        String[] inputs = {
            "101-1201,김도윤,250,18,50000,A",
            "102-1502,이하린,310,22,65000,B",
            "103-1803,박서아,420,30,80000,C"
        };

        ArrayList<ApartmentData> list = new ArrayList<>();
        for (String input : inputs) {
            list.add(new ApartmentData(input));
        }

        System.out.println("동호수,세대주명,전기요금,수도요금,총관리비,평형명");
        for (ApartmentData data : list) {
            data.process();
        }
    }
}
