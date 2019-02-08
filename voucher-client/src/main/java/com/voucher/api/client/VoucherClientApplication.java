package com.voucher.api.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
public class VoucherClientApplication implements CommandLineRunner {

	private static Logger LOG = LoggerFactory.getLogger(VoucherClientApplication.class);

	private final RestTemplate restTemplate;

	private String clientEndpoint;
	private String voucherEndpoint;

	@Autowired
	public VoucherClientApplication(RestTemplateBuilder restTemplate,
									@Value("${voucher.api.v1.client}") String clientEndpoint,
									@Value("${voucher.api.v1.voucher}") String voucherEndpoint) {
		this.restTemplate = restTemplate.build();
		this.clientEndpoint = clientEndpoint;
		this.voucherEndpoint = voucherEndpoint;
	}

	public static void main(String[] args) {
		LOG.info("STARTING THE APPLICATION");

		SpringApplication.run(VoucherClientApplication.class, args);

		LOG.info("APPLICATION FINISHED");
	}

	@Override
	public void run(String... args) {
		LOG.info("EXECUTING : command line runner");

		LOG.info(restTemplate.getForEntity(clientEndpoint, String.class).getBody());
		LOG.info(restTemplate.getForEntity(voucherEndpoint, String.class).getBody());

		for (int i = 0; i < args.length; ++i) {
			LOG.info("args[{}]: {}", i, args[i]);
		}
	}
}
