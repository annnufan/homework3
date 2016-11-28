package ru.ifmo.droid2016.imageapp.loader;

/**
 * Три возможных результат процесса загрузки данных.
 */
public enum ResultType {

    /**
     * Данные успешно загружены.
     */
    OK,

    /**
     * Данные не загружены из-за отсутствия интернета.
     */
    NO_INTERNET
}
