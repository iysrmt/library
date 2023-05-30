package per.iys.library;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class HutoolTest {

    @Test
    public void uuidTest() {
        log.info("{}", IdUtil.fastSimpleUUID());
    }

    @Test
    public void md5Test() {
        log.info("{}", SecureUtil.md5("admin"));
        log.info("{}", SecureUtil.md5("iys"));
        log.info("{}", SecureUtil.md5("user"));
    }

    @Test
    public void dateUtilTest() {
        DateTime date = DateUtil.date();
        // String now = DateUtil.now();
        DateTime dateTime = DateUtil.nextMonth();
        log.info("{}", dateTime);
    }

}
