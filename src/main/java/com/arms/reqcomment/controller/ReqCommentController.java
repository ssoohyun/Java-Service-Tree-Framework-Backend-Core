/*
 * @author Dongmin.lee
 * @since 2023-03-01
 * @version 23.03.01
 * @see <pre>
 *  Copyright (C) 2007 by 313 DEV GRP, Inc - All Rights Reserved
 *  Unauthorized copying of this file, via any medium is strictly prohibited
 *  Proprietary and confidential
 *  Written by 313 developer group <313@313.co.kr>, December 2010
 * </pre>
 */
package com.arms.reqcomment.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

import com.egovframework.ple.treeframework.springhibernate.controller.SHVAbstractController;

import com.arms.reqcomment.model.ReqCommentDTO;
import com.arms.reqcomment.service.ReqComment;

@Slf4j
@Controller
@RequestMapping(value = {"/arms/reqComment"})
public class ReqCommentController extends SHVAbstractController<ReqComment, ReqCommentDTO> {

    @Autowired
    @Qualifier("reqComment")
    private ReqComment reqComment;

    @PostConstruct
    public void initialize() {
        setJsTreeHibernateService(reqComment);
    }

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

}
