package com.yj.reservation.common.security;

import com.yj.reservation.common.security.validate.PrivilegeVerification;
import com.yj.reservation.common.security.validate.Verification;
import com.yj.reservation.common.util.LoggerUtil;
import com.yj.reservation.common.util.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ValidateChain implements ApplicationRunner {


    private final List<Verification> validateList = new ArrayList<>();
    @Autowired
    private Verification privilegeVerification;


    public final void addVerification(Verification verification) {
        if (verification != null) {
            validateList.add(verification);
        }
    }

    public final List<Verification> list() {
        return validateList;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        //当实现ApplicationRunner中的run后，在spring依赖注入初始化后，调用
        LoggerUtil.info("初始化ValidateChain 此时spring是否注入了privilegeVerification：" + privilegeVerification);
        validateList.add(privilegeVerification);
    }
}
