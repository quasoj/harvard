package edu.harvard.academics.service.impl;

import edu.harvard.academics.entity.OperationLog;
import edu.harvard.academics.mapper.OperationLogMapper;
import edu.harvard.academics.service.OperationLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OperationLogServiceImpl implements OperationLogService {

    private final OperationLogMapper operationLogMapper;

    public OperationLogServiceImpl(OperationLogMapper operationLogMapper) {
        this.operationLogMapper = operationLogMapper;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW) // default: Propagation.REQUIRED
    public void add(OperationLog operationLog) {
        operationLogMapper.add(operationLog);
    }

}
