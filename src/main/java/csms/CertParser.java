/*
 * Copyright (c) 2023 ivfzhou
 * cert_parser is licensed under Mulan PSL v2.
 * You can use this software according to the terms and conditions of the Mulan PSL v2.
 * You may obtain a copy of Mulan PSL v2 at:
 *          http://license.coscl.org.cn/MulanPSL2
 * THIS SOFTWARE IS PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OF ANY KIND,
 * EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO NON-INFRINGEMENT,
 * MERCHANTABILITY OR FIT FOR A PARTICULAR PURPOSE.
 * See the Mulan PSL v2 for more details.
 */

package csms;

import jakarta.annotation.Resource;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.IFactory;

import csms.cmd.Certificate;
import csms.cmd.Provision;

@SpringBootApplication
@Command(
        name = "cert_parser",
        mixinStandardHelpOptions = true,
        version = "0.1.0",
        subcommands = {Certificate.class, Provision.class}
)
public class CertParser implements ApplicationRunner {

    @Resource
    private IFactory factory;

    public static void main(String[] args) {
        SpringApplication.run(CertParser.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        CommandLine commandLine = new CommandLine(this, factory);
        commandLine.setCaseInsensitiveEnumValuesAllowed(true);
        commandLine.execute(args.getSourceArgs());
    }
}
