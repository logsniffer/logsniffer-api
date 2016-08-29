package com.logsniffer.event;

import java.io.IOException;

import com.logsniffer.model.LogFragment;

public interface ScannerStream extends ScannerContext {
	void push(LogFragment lf) throws IOException;

}
