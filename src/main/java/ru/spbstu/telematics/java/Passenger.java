package ru.spbstu.telematics.java;

class Passenger {
    private static int idCounter = 1;
    private final int id;

    public Passenger() {
        this.id = idCounter++;
    }

    public int getId() {
        return id;
    }
}
