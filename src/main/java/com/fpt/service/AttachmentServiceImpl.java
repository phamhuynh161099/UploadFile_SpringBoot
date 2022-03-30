package com.fpt.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fpt.entity.Attachment;
import com.fpt.repository.AttachmentRepository;

@Service
public class AttachmentServiceImpl implements AttachmentService {

	private AttachmentRepository attachmentRepository;

	public AttachmentServiceImpl(AttachmentRepository attachmentRepository) {
		this.attachmentRepository = attachmentRepository;
	}

	@Override
	public Attachment saveAttachment(MultipartFile file) throws Exception {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		try {
			if (fileName.contains("..")) {
				throw new Exception("Filename contain invalid path sequence " + fileName);
			}

			Attachment attachment = new Attachment(fileName, file.getContentType(), file.getBytes());

			return attachmentRepository.save(attachment);

		} catch (Exception e) {
			throw new Exception("Could not save file " + fileName);
		}
	}

	@Override
	public Attachment getAttachment(String fileId) throws Exception {
		
		return attachmentRepository
				.findById(fileId)
				.orElseThrow(()->new Exception("File not Found with id: "));
	}
	
	
	

}
