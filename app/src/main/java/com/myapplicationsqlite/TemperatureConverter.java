package com.myapplicationsqlite;


public class TemperatureConverter {
    // Celsius to Fahrenheit
    public static double celsiusToFarenheit(double celsius) {
        return (celsius * 9 / 5) + 32;
    }

    // Fahrenheit to Celsius
    public static double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5 / 9;
    }

    // Celsius to Kelvin
    public static double celsiusToKelvin(double celsius) {
        return celsius + 273.15;
    }

    // Kelvin to Celsius
    public static double kelvinToCelsius(double kelvin) {
        return kelvin - 273.15;
    }

    // Fahrenheit to Kelvin
    public static double fahrenheitToKelvin(double fahrenheit) {
        double celsius = fahrenheitToCelsius(fahrenheit);
        return celsiusToKelvin(celsius);
    }

    // Kelvin to Fahrenheit
    public static double kelvinToFarenheit(double kelvin) {
        double celsius = kelvinToCelsius(kelvin);
        return celsiusToFarenheit(celsius);
    }
}