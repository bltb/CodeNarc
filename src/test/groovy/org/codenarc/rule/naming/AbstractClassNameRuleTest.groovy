/*
 * Copyright 2009 the original author or authors.
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
package org.codenarc.rule.naming

import org.codenarc.rule.AbstractRuleTest
import org.codenarc.rule.Rule

/**
 * Tests for AbstractClassNameRule
 *
 * @author Chris Mair
 * @version $Revision: 37 $ - $Date: 2009-02-06 21:31:05 -0500 (Fri, 06 Feb 2009) $
 */
class AbstractClassNameRuleTest extends AbstractRuleTest {

    void testRuleProperties() {
        assert rule.priority == 2
        assert rule.name == 'AbstractClassName'
        assert rule.regex == null
    }

    void testRegexIsNull() {
        final SOURCE = 'abstract class aaa$bbb{ }'
        assert !rule.ready
        assertNoViolations(SOURCE)
    }

    void testApplyTo_WithPackage_MatchesRegex() {
        final SOURCE = '''
            package org.codenarc.sample
            abstract class AbstractClass { }
        '''
        rule.regex = /[A-Z].*/
        assertNoViolations(SOURCE)
    }

    void testApplyTo_DoesNotMatchRegex() {
        final SOURCE = " abstract class AbstractClass { } "
        rule.regex = /z.*/
        assertSingleViolation(SOURCE, 1, 'AbstractClass')
    }

    void testApplyTo_MatchesRegex() {
        final SOURCE = " abstract class zClass { } "
        rule.regex = /z.*/
        assertNoViolations(SOURCE)
    }

    void testApplyTo_NonAbstractClass() {
        final SOURCE = " class nonAbstract$Class { } "
        rule.regex = /[A-Z].*/
        assertNoViolations(SOURCE)
    }

    void testApplyTo_Interface() {
        final SOURCE = " interface interface$Class { } "
        rule.regex = /[A-Z].*/
        assertNoViolations(SOURCE)
    }

    void testApplyTo_NoClassDefinition() {
        rule.regex = /[A-Z].*/
        final SOURCE = '''
            if (isReady) {
                println 'ready'
            }
        '''
        assertNoViolations(SOURCE)
    }

    protected Rule createRule() {
        return new AbstractClassNameRule()
    }

}