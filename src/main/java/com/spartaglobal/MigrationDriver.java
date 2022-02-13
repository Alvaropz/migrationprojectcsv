package com.spartaglobal;

import com.spartaglobal.controller.MigrationManager;

public class MigrationDriver {
    public static void main(String[] args) {
        MigrationManager migration = new MigrationManager();
        migration.run();
    }
}