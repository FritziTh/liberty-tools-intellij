/*******************************************************************************
 * Copyright (c) 2022 IBM Corporation.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 *  SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/

package io.openliberty.tools.intellij;

import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Singleton to save the LibertyModules in the open project
 */
public class LibertyModules {

    private static LibertyModules instance = null;

    // key is build file associated with the Liberty project
    Map<VirtualFile, LibertyModule> libertyModules;

    private LibertyModules() {
        libertyModules = new HashMap<>();
    }

    public static LibertyModules getInstance() {
        if (instance == null) {
            instance = new LibertyModules();
        }
        return instance;
    }

    /**
     * Add tracked Liberty project to workspace
     * @param module LibertyModule
     */
    public void addLibertyModule(LibertyModule module) {
        libertyModules.put(module.getBuildFile(), module);
    }

    /**
     * Get a Liberty module associated with the corresponding build file
     * @param buildFile build file
     * @return LibertyModule
     */
    public LibertyModule getLibertyModule(VirtualFile buildFile) {
        return libertyModules.get(buildFile);
    }

    /**
     * Returns the Liberty project associated with a build file
     *
     * @param buildFile String, path to build file
     * @return LibertyModule
     * @throws MalformedURLException
     */
    public LibertyModule getLibertyProjectFromString(String buildFile) throws MalformedURLException {
        VirtualFile vBuildFile = VfsUtil.findFileByURL(new URL(buildFile));
        return libertyModules.get(vBuildFile);
    }

    /**
     * Returns all build files associated with a Liberty project
     * @return List<VirtualFile> Liberty project build files
     */
    public List<VirtualFile> getLibertyBuildFiles() {
        List<VirtualFile> buildFiles = new ArrayList<>();
        buildFiles.addAll(libertyModules.keySet());
        return buildFiles;
    }

    /**
     * Returns all Liberty modules in the workspace
     * @return List<LibertyModule> Liberty project modules
     */
    public List<LibertyModule> getLibertyModules() {
        return new ArrayList(libertyModules.values());
    }
}
