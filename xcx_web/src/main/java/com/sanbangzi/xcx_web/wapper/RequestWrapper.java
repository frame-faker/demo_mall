package com.sanbangzi.xcx_web.wapper;

import lombok.Getter;
import org.apache.commons.io.IOUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class RequestWrapper extends HttpServletRequestWrapper {

    @Getter
    private final String body;

    public RequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        body =  IOUtils.toString(request.getInputStream(), "UTF-8");
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        byte[] by = body.getBytes();
        ServletInputStream inStream  = new CustomServletInputStream(by);
        return inStream;
    }

    @Override
    public BufferedReader getReader() throws IOException {
        InputStreamReader inStream = new InputStreamReader(this.getInputStream());
        return new BufferedReader(inStream);
    }

    private static class CustomServletInputStream extends ServletInputStream {
        private ByteArrayInputStream buffer;

        public CustomServletInputStream(byte[] contents) {
            this.buffer = new ByteArrayInputStream(contents);
        }

        @Override
        public int read() throws IOException {
            return buffer.read();
        }

        @Override
        public boolean isFinished() {
            return buffer.available() == 0;
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setReadListener(ReadListener listener) {
            throw new RuntimeException("not implemented");
        }
    }
}
