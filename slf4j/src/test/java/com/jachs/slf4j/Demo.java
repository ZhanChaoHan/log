package com.jachs.slf4j;

import lombok.extern.slf4j.Slf4j;

/***
 * 级别排序 error、warn、info、debug、trane
 * @author zhanchaohan
 *
 */
@Slf4j
public class Demo {
	public static void main(String[] args) {
		log.trace("TRACE");
		log.debug("DEBUG A");
		log.warn("WARN A");
		
		log.info("INFO 啊");
		log.info("INFO 啊{}","嗯嗯嗯");
		
		log.error("ERROR A");
	}
}
