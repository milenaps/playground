package com.mps.pocs;

import org.junit.Assert;
import org.junit.Test;

public class DateUtilsTest {

	private DateUtils util = new DateUtils();

	@Test
	public void changeDate_1() throws Exception {
		Assert.assertEquals("04/03/2010 17:40", util.changeDate("01/03/2010 23:00", '+', 4000));
	}

	@Test
	public void changeDate_2() throws Exception {
		Assert.assertEquals("04/03/2010 17:40", util.changeDate("01/03/2010 23:00", '+', -4000));
	}

	@Test
	public void changeDate_3() throws Exception {
		Assert.assertThrows(Exception.class, () -> util.changeDate("", '1', 1000));
	}
}