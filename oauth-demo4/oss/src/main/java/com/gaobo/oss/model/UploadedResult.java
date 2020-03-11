package com.gaobo.oss.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class UploadedResult {

	protected List<Uploaded> uploaded;

	protected List<NotUploaded> notUploaded;

	public UploadedResult() {
	}

	public List<Uploaded> getUploaded() {
		if (this.uploaded == null) {
			this.uploaded = new ArrayList<>();
		}

		return this.uploaded;
	}

	public List<NotUploaded> getNotUploaded() {
		if (this.notUploaded == null) {
			this.notUploaded = new ArrayList<>();
		}

		return this.notUploaded;
	}

	@Data
	public static class Uploaded {

		protected String location;
	}

	@Data
	public static class NotUploaded {

		protected String location;

	}
}
