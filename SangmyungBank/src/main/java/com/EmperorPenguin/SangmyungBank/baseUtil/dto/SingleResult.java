package com.EmperorPenguin.SangmyungBank.baseUtil.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SingleResult<T> extends BaseResult{
    T Data;
}
