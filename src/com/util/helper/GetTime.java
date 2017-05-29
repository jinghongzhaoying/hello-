package com.util.helper;

import java.sql.Timestamp;
import java.util.Date;

public class GetTime {
	public static Timestamp getTime() {
		return new Timestamp(new Date().getTime());
	}
}
