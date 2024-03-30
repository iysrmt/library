package per.iys.library.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelWriter;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import per.iys.library.commons.domain.Result;
import per.iys.library.domain.DictValue;
import per.iys.library.domain.User;
import per.iys.library.service.DictValueService;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/system/settings")
public class SettingController {

    private final DictValueService dictValueService;

    @Autowired
    public SettingController(DictValueService dictValueService) {
        this.dictValueService = dictValueService;
    }

    /**
     * 跳转到字段页面
     */
    @GetMapping("/dict")
    public String toDict(Model model) {
        List<DictValue> categoryList = dictValueService.queryDictValueListByTypeCode("category");
        model.addAttribute("categoryList", categoryList);
        return "system/settings/dict";
    }

    /**
     * 下载 Excel (xlsx) 模板
     */
    @GetMapping(value = "/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void downloadExcel(HttpSession session, HttpServletResponse response) throws IOException {

        User sessionUser = (User) session.getAttribute("sessionUser");

        if (!"1".equals(sessionUser.getPermissions())) {
            return;
        }

        List<String> row1 = CollUtil.newArrayList("value", "orderNo");
        List<String> row2 = CollUtil.newArrayList("分类1", "1");
        List<String> row3 = CollUtil.newArrayList("分类2", "2");
        List<List<String>> rows = CollUtil.newArrayList(row1, row2, row3);

        response.addHeader("Content-Disposition", "attachment;filename=Dict.xlsx");
        ServletOutputStream out = response.getOutputStream();

        ExcelWriter writer = new ExcelWriter(true);
        writer.write(rows);
        writer.flush(out);

        writer.close();
        out.close();
    }

    /**
     * 上传 Excel (xlsx) 更新分类
     */
    @PostMapping("/upload")
    public @ResponseBody Result<?> uploadExcel(HttpSession session, MultipartFile file) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (!"1".equals(sessionUser.getPermissions())) {
            return Result.fail().message("非法操作!");
        }

        // 文件名
        String filename = file.getOriginalFilename();
        if (filename != null && !filename.toLowerCase().endsWith(".xlsx")) {
            return Result.fail().message("仅支持 xlsx 格式!");
        }

        InputStream in;

        try {
            in = file.getInputStream();
            ExcelReader reader = new ExcelReader(in, 0);
            List<DictValue> dictValueList = reader.readAll(DictValue.class);
            for (int i = 0; i < dictValueList.size(); i++) {
                DictValue dictValue = dictValueList.get(i);
                dictValue.setId(IdUtil.fastSimpleUUID());
                dictValue.setTypeCode("category");
                dictValueList.set(i, dictValue);
            }
            dictValueService.saveDictValueByTypeCodeList("category", dictValueList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return Result.ok();
    }
}
