package lecture.one.bitwise;

public class Main {

    public static void main(String[] args) {
        int a = 5;    // 00000000 00000000 00000000 00000101
        int b = 3;    // 00000000 00000000 00000000 00000011
        int c = -8;   // 11111111 11111111 11111111 11111000 (two's complement)

        // Побитовое И (AND)
        int andResult = a & b;
        System.out.println("5 & 3 = " + andResult +
                " | Binary: " + Integer.toBinaryString(andResult));

        // Побитовое ИЛИ (OR)
        int orResult = a | b;
        System.out.println("5 | 3 = " + orResult +
                " | Binary: " + Integer.toBinaryString(orResult));

        // Побитовое XOR
        int xorResult = a ^ b;
        System.out.println("5 ^ 3 = " + xorResult +
                " | Binary: " + Integer.toBinaryString(xorResult));

        // Побитовое НЕ (NOT)
        int notResult = ~a;
        System.out.println("~5 = " + notResult +
                " | Binary: " + Integer.toBinaryString(notResult));

        // Сдвиг влево
        int leftShift = a << 1;
        System.out.println("5 << 1 = " + leftShift +
                " | Binary: " + Integer.toBinaryString(leftShift));

        // Сдвиг вправо с сохранением знака
        int signedShift = c >> 2;
        System.out.println("-8 >> 2 = " + signedShift +
                " | Binary: " + Integer.toBinaryString(signedShift));

        // Беззнаковый сдвиг вправо (заполнение нулями)
        int unsignedShiftRight = c >>> 2;
        System.out.println("-8 >>> 2 = " + unsignedShiftRight +
                " | Binary: " + Integer.toBinaryString(unsignedShiftRight));

        // Сдвиг влево (обычный, работает и для отрицательных)
        int unsignedShiftLeft = c << 2;
        System.out.println("-8 << 2 = " + unsignedShiftLeft +
                " | Binary: " + Integer.toBinaryString(unsignedShiftLeft));
    }
}