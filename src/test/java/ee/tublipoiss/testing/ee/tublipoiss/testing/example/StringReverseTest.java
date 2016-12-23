/*
 * Copyright (C) Fortumo OÜ, 2016
 * All rights reserved. Proprietary and confidential.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 *
 * All rights reserved. Proprietary and confidential.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Distribution, modification, reproduction, merging, publishing, sub-licensing,
 * sale and/or any other use (in binary form or otherwise) of this software is
 * not permitted, unless Fortumo OÜ has explicitly and in writing permitted such
 * use of the software.
 *
 * THIS SOFTWARE IS PROVIDED BY COPYRIGHT HOLDER ''AS IS'' AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL Fortumo OÜ BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package ee.tublipoiss.testing.ee.tublipoiss.testing.example;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class StringReverseTest {

    public static String reverse(String s) {
        List<String> tempArray = new ArrayList<String>(s.length());
        for (int i = 0; i < s.length(); i++) {
            tempArray.add(s.substring(i, i + 1));
        }
        StringBuilder reversedString = new StringBuilder(s.length());
        for (int i = tempArray.size() - 2; i >= 0; i--) {
            reversedString.append(tempArray.get(i));
        }
        return reversedString.toString();
    }
/*
    @Test
    public void should_reverse() {
        StringReverser stringReverser = new StringReverse();
        String original = "Oskar";
        String reversed = "raksO";

        assertThat(reverse(original)).isEqualTo(reversed);
    }
    */
}