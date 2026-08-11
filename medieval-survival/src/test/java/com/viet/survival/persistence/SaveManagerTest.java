package com.viet.survival.persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class SaveManagerTest {

    private SaveManager saveManager;

    @BeforeEach
    void setUp() {
        saveManager = new SaveManager();
    }

    @Test
    void isExceptionThrownWhenIllegalVillagerType() {
        assertThrows(IllegalArgumentException.class,
                () -> saveManager.deserializeVillager("""
                {"villagerType": "IllegalVillagerType"}
                """));
    }
}
