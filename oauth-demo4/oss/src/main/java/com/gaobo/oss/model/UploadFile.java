package com.gaobo.oss.model;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class UploadFile implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<String> paths = new ArrayList<String>();

	private byte[] content = new byte[0];

}
