/*
 * Copyright 2008 the original author or authors.
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
package org.codenarc.rule.exceptions

import org.codenarc.rule.AbstractRuleTest
import org.codenarc.rule.Rule
import org.codenarc.rule.exceptions.CatchThrowableRule

/**
 * Tests for CatchThrowableRule
 *
 * @author Chris Mair
 * @version $Revision$ - $Date$
 */
class CatchThrowableRuleTest extends AbstractRuleTest {

    void testRuleProperties() {
        assert rule.priority == 2
        assert rule.id == 'CatchThrowable'
    }

    void testApplyTo_Violation() {
        final SOURCE = '''
            class MyClass {
                def myMethod() {
                    try {

                    } catch(Throwable t) {
                    }
                }
            }
        '''
        assertSingleViolation(SOURCE, 6, 'catch(Throwable t) {')
    }

    void testApplyTo_NoViolations() {
        final SOURCE = '''class MyClass {
                def myMethod() {
                    try {
                    } catch(Exception t) { }
                }
            }'''
        assertNoViolations(SOURCE)
    }

    protected Rule createRule() {
        return new CatchThrowableRule()
    }

}