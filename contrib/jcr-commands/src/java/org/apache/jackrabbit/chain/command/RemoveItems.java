/*
 * Copyright 2004-2005 The Apache Software Foundation or its licensors,
 *                     as applicable.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.jackrabbit.chain.command;

import java.util.Iterator;

import javax.jcr.Item;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.apache.jackrabbit.chain.CtxHelper;

/**
 * Removes any item under the current working node that match the given name
 * pattern. <br>
 * The Command attributes are set from the specified literal values, or from the
 * context attributes stored under the given keys.
 */
public class RemoveItems implements Command
{

    // ---------------------------- < literals >

    /** item pattern */
    private String pattern;

    // ---------------------------- < keys >
    /** item pattern key */
    private String patternKey;

    /**
     * @inheritDoc
     */
    public boolean execute(Context ctx) throws Exception
    {
        String pattern = CtxHelper.getAttr(this.pattern,
            this.patternKey, ctx);

        Iterator items = CtxHelper.getItems(ctx, pattern);

        while (items.hasNext())
        {
            Item item = (Item) items.next();
            item.remove();
        }

        return false;
    }

    public String getPattern()
    {
        return pattern;
    }

    public void setPattern(String pattern)
    {
        this.pattern = pattern;
    }

    /**
     * @return Returns the patternKey.
     */
    public String getPatternKey()
    {
        return patternKey;
    }

    /**
     * @param patternKey
     *            Set the context attribute key for the pattern attribute.
     */
    public void setPatternKey(String patternKey)
    {
        this.patternKey = patternKey;
    }
}
