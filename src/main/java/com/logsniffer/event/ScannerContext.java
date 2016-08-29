package com.logsniffer.event;

import java.io.IOException;

import net.sf.json.JSONObject;

public interface ScannerContext {
	JSONObject saveState() throws IOException;
}
