package com.example;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

import java.util.ArrayList;
import java.util.List;

public class Application {


    public static void main(String[] args) {
        new Application().start();
    }

    private void start() {

        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setResource("HelloWorld");
// set limit qps to 20
        rule.setCount(20);
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rules.add(rule);
        FlowRuleManager.loadRules(rules);


        for (int i = 0; i < 25; i++) {


            try (Entry entry = SphU.entry("HelloWorld")) {
                // Your business logic here.
                System.out.println("hello world " + (i + 1));
            } catch (BlockException e) {
                // Handle rejected request.
                e.printStackTrace();
            }

        }

    }

}
