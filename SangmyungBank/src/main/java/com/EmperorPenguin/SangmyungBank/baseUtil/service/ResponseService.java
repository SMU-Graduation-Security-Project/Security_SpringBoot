package com.EmperorPenguin.SangmyungBank.baseUtil.service;

import com.EmperorPenguin.SangmyungBank.baseUtil.dto.BaseResult;
import com.EmperorPenguin.SangmyungBank.baseUtil.dto.ListResult;
import com.EmperorPenguin.SangmyungBank.baseUtil.dto.SingleResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseService {

    public BaseResult successResult() {
        return setOk(new BaseResult());
    }

    public BaseResult failResult(String message){
        return setFail(new BaseResult(), message);
    }

    public <T> SingleResult<T> singleResult(T data){
        SingleResult<T> singleResult = new SingleResult<>();
        singleResult.setData(data);
        setOk(singleResult);
        return singleResult;
    }

    public <T> ListResult<T> listResult(List<T> data){
        ListResult<T> listResult = new ListResult<>();
        setOk(listResult);
        listResult.setData(data);
        return listResult;
    }

    private BaseResult setOk(BaseResult baseResult){
        baseResult.setChecker(true);
        baseResult.setMessage("SUCCESS");
        return baseResult;
    }

    private BaseResult setFail(BaseResult baseResult, String message){
        baseResult.setChecker(false);
        baseResult.setMessage(message);
        return baseResult;
    }
}
