package com.epam.lab.hospitalspring.controller;

import org.junit.Test;

import static org.junit.Assert.*;

public class IndexTest {

    @Test
    public void testShowLoginPageAsAdmin() {
        InitRoles.initAdmin();
    }

    @Test
    public void testShowLoginPageAsDoctor() {
        InitRoles.initDoctor();
    }

    @Test
    public void testShowLoginPageAsNurse() {
        InitRoles.initNurse();
    }
}