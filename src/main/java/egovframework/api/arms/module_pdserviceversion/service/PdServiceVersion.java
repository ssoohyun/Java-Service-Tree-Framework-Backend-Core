/*
 * @author Dongmin.lee
 * @since 2022-11-20
 * @version 22.11.20
 * @see <pre>
 *  Copyright (C) 2007 by 313 DEV GRP, Inc - All Rights Reserved
 *  Unauthorized copying of this file, via any medium is strictly prohibited
 *  Proprietary and confidential
 *  Written by 313 developer group <313@313.co.kr>, December 2010
 * </pre>
 */
package egovframework.api.arms.module_pdserviceversion.service;

import egovframework.api.arms.module_pdserviceversion.model.PdServiceVersionDTO;
import egovframework.com.ext.jstree.springHibernate.core.service.JsTreeHibernateService;

import java.util.List;

public interface PdServiceVersion extends JsTreeHibernateService {

    public List<PdServiceVersionDTO> getVersionListByPdService(PdServiceVersionDTO pdServiceVersionDTO) throws Exception;

    public List<PdServiceVersionDTO> getVersionListByCids(List<Long> pdServiceVersionDTO) throws Exception;
}