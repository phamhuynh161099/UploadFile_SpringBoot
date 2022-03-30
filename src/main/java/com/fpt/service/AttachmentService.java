package com.fpt.service;

import org.springframework.web.multipart.MultipartFile;

import com.fpt.entity.Attachment;

public interface AttachmentService {

	Attachment saveAttachment(MultipartFile file) throws Exception;

	Attachment getAttachment(String fileId) throws Exception;

}
