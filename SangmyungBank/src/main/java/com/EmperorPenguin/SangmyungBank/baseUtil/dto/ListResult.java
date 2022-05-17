package com.EmperorPenguin.SangmyungBank.baseUtil.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ListResult<T> extends BaseResult{
    List<T> Data;
}
