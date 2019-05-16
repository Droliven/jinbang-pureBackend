{
    "后端网址": "11pm.top:8081/jinbang",
    "后端网络请求接口": [{
        "index 页": {
            "method": "get/post",
            "url": "http://11pm.top:8081/jinbang",
            "response": {
                "@type": "java.util.HashMap",
                "success": "index page"
            }
        }
    }, {
        "登录": {
            "method": "post",
            "data": {
                "name": "建国",
                "pwd": "123456"
            }, 
            "url": "http://11pm.top:8081/jinbang/home",
            "FailResponse": {
                "@type": "java.util.HashMap",
                "err": "Wrong pwd! / Name not exists!"
            },
            "SuccessResponse": {
                "@type": "java.util.HashMap",
                "seccess": "Login successful!"
            }
        }
    }, {
        "单选框选项值获取": {
            "method": "get",
            "url": "http://11pm.top:8081/jinbang/itemradio",
            "FailResponse": {
                "@type": "java.util.HashMap",
                "err": "Not Loged!"
            },
            "SuccessResponse": {
                "@type": "java.util.HashMap",
                "itemradio": [{
                    "@type": "java.util.HashMap",
                    "types": [
                        "翻译",
                        "计算",
                        "填空",
                        "选择"
                    ]
                }, {
                    "@type": "java.util.HashMap",
                    "sources": [
                        "21世纪教育在线题库",
                        "宝山区2016届高三上学期期末",
                        "上大附中2019年第一次月考",
                        "上海财经大学附属中学2016年9月月考",
                        "自编"
                    ]
                }, {
                    "@type": "java.util.HashMap",
                    "grades": [
                        "初中二年级",
                        "初中一年级",
                        "高中三年级",
                        "高中一年级",
                        "小学三年级",
                        "小学一年级"
                    ]
                }, {
                    "@type": "java.util.HashMap",
                    "names": [
                        "翠花",
                        "建国",
                        "铁柱"
                    ]
                }, {
                    "@type": "java.util.HashMap",
                    "paths": [{
                        "@type": "com.alibaba.fastjson.JSONObject",
                        "数学": [{
                            "@type": "com.alibaba.fastjson.JSONObject",
                            "四则混合运算": []
                        }, {
                            "@type": "com.alibaba.fastjson.JSONObject",
                            "对数运算": []
                        }, {
                            "@type": "com.alibaba.fastjson.JSONObject",
                            "一元二次方程换元法": []
                        }, {
                            "@type": "com.alibaba.fastjson.JSONObject",
                            "十以内加法运算": []
                        }]
                    }, {
                        "@type": "com.alibaba.fastjson.JSONObject",
                        "英语": [{
                            "@type": "com.alibaba.fastjson.JSONObject",
                            "时态": [{
                                "@type": "com.alibaba.fastjson.JSONObject",
                                "将来进行时": []
                            }]
                        }, {
                            "@type": "com.alibaba.fastjson.JSONObject",
                            "宾语从句": []
                        }, {
                            "@type": "com.alibaba.fastjson.JSONObject",
                            "现在完成时": []
                        }, {
                            "@type": "com.alibaba.fastjson.JSONObject",
                            "现在进行时": []
                        }, {
                            "@type": "com.alibaba.fastjson.JSONObject",
                            "最高级": []
                        }, {
                            "@type": "com.alibaba.fastjson.JSONObject",
                            "过去完成时": []
                        }, {
                            "@type": "com.alibaba.fastjson.JSONObject",
                            "情态动词": [{
                                "@type": "com.alibaba.fastjson.JSONObject",
                                "should": []
                            }]
                        }, {
                            "@type": "com.alibaba.fastjson.JSONObject",
                            "固定短语": []
                        }]
                    }, {
                        "@type": "com.alibaba.fastjson.JSONObject",
                        "物理": [{
                            "@type": "com.alibaba.fastjson.JSONObject",
                            "电学": [{
                                "@type": "com.alibaba.fastjson.JSONObject",
                                "电功与电热": []
                            }]
                        }]
                    }]
                }]
            }
        }
    }, {
        "获取全部试题（前端可加入分页功能）": {
            "method": "get",
            "url": "http://11pm.top:8081/itemall",
            "FailResponse": {
                "@type": "java.util.HashMap",
                "err": "Not Loged!"
            },
            "SuccessResponse": {
                "@type": "java.util.HashMap",
                "itemall": [{
                    "@type": "com.jinbang.model.Item_Asr_Usr_IK_Kp",
                    "answer": {
                        "asrid": 1,
                        "content": "11"
                    },
                    "item": {
                        "asrid": 1,
                        "content": "三（1）班小朋友做玩具，一共做了48个，送给幼儿园15个，其余的平均分给一年级3个班，每班可以分得____个.",
                        "difficulty": 5,
                        "grade": "小学三年级",
                        "iid": 1,
                        "source": "上大附中2019年第一次月考",
                        "type": "填空",
                        "uid": 1
                    },
                    "item_kps": [{
                        "degree": 5,
                        "iid": 1,
                        "kpid": 2
                    }],
                    "kPPath": "数学/四则混合运算",
                    "knowledgepoints": [{
                        "depth": 2,
                        "knowledgepoint": "四则混合运算",
                        "kpid": 2,
                        "prepoint": 1
                    }],
                    "user": {
                        "authority": "录入组卷员",
                        "name": "建国",
                        "uid": 1
                    }
                }, {
                    "@type": "com.jinbang.model.Item_Asr_Usr_IK_Kp",
                    "answer": {
                        "asrid": 2,
                        "content": "By the time this railroad has been extended to the remote small city, the local economy is sure to develop/be developed very fast."
                    },
                    "item": {
                        "asrid": 2,
                        "content": "等这条铁路延伸至那座偏远的小城之后，当地的经济一定会得到迅猛的发展。（by the time）",
                        "difficulty": 5,
                        "grade": "高中三年级",
                        "iid": 2,
                        "source": "上海财经大学附属中学2016年9月月考",
                        "type": "翻译",
                        "uid": 1
                    },
                    "item_kps": [{
                        "degree": 5,
                        "iid": 2,
                        "kpid": 5
                    }],
                    "kPPath": "英语/时态/将来进行时",
                    "knowledgepoints": [{
                        "depth": 3,
                        "knowledgepoint": "将来进行时",
                        "kpid": 5,
                        "prepoint": 4
                    }],
                    "user": {
                        "authority": "录入组卷员",
                        "name": "建国",
                        "uid": 1
                    }
                }, {
                    "@type": "com.jinbang.model.Item_Asr_Usr_IK_Kp",
                    "answer": {
                        "asrid": 3,
                        "content": "x=ln(4)/ln(2)"
                    },
                    "item": {
                        "asrid": 3,
                        "content": "计算方程 4^x-2^x-6=0 的解.",
                        "difficulty": 5,
                        "grade": "高中三年级",
                        "iid": 3,
                        "source": "宝山区2016届高三上学期期末",
                        "type": "计算",
                        "uid": 1
                    },
                    "item_kps": [{
                        "degree": 5,
                        "iid": 3,
                        "kpid": 6
                    }, {
                        "degree": 5,
                        "iid": 3,
                        "kpid": 7
                    }],
                    "kPPath": "数学/一元二次方程换元法,数学/对数运算",
                    "knowledgepoints": [{
                        "depth": 2,
                        "knowledgepoint": "对数运算",
                        "kpid": 6,
                        "prepoint": 1
                    }, {
                        "depth": 2,
                        "knowledgepoint": "一元二次方程换元法",
                        "kpid": 7,
                        "prepoint": 1
                    }],
                    "user": {
                        "authority": "录入组卷员",
                        "name": "建国",
                        "uid": 1
                    }
                }, {
                    "@type": "com.jinbang.model.Item_Asr_Usr_IK_Kp",
                    "answer": {
                        "asrid": 4,
                        "content": "13"
                    },
                    "item": {
                        "asrid": 4,
                        "content": "5+8=____.",
                        "difficulty": 5,
                        "grade": "小学一年级",
                        "iid": 4,
                        "source": "自编",
                        "type": "填空",
                        "uid": 2
                    },
                    "item_kps": [{
                        "degree": 5,
                        "iid": 4,
                        "kpid": 8
                    }],
                    "kPPath": "数学/十以内加法运算",
                    "knowledgepoints": [{
                        "depth": 2,
                        "knowledgepoint": "十以内加法运算",
                        "kpid": 8,
                        "prepoint": 1
                    }],
                    "user": {
                        "authority": "录入组卷员",
                        "name": "铁柱",
                        "uid": 2
                    }
                }, {
                    "@type": "com.jinbang.model.Item_Asr_Usr_IK_Kp",
                    "answer": {
                        "asrid": 5,
                        "content": "A"
                    },
                    "item": {
                        "asrid": 5,
                        "content": "清华大学的虞昊教授致力于第三代照明技术——LED灯的普及工作。LED灯的能耗小，造价低廉，使用寿命长.虞昊自制的LED台灯，用电压为6伏的电源供电，正常工作电流为300毫安。那么，此LED台灯正常工作时的功率为(  ). <br/> A．1.8瓦B．18瓦C．180瓦D．1800瓦",
                        "difficulty": 5,
                        "grade": "初中二年级",
                        "iid": 5,
                        "source": "21世纪教育在线题库",
                        "type": "选择",
                        "uid": 3
                    },
                    "item_kps": [{
                        "degree": 5,
                        "iid": 5,
                        "kpid": 11
                    }],
                    "kPPath": "物理/电学/电功与电热",
                    "knowledgepoints": [{
                        "depth": 3,
                        "knowledgepoint": "电功与电热",
                        "kpid": 11,
                        "prepoint": 10
                    }],
                    "user": {
                        "authority": "录入组卷员",
                        "name": "翠花",
                        "uid": 3
                    }
                }, {
                    "@type": "com.jinbang.model.Item_Asr_Usr_IK_Kp",
                    "answer": {
                        "asrid": 6,
                        "content": "if your pen friend has replied"
                    },
                    "item": {
                        "asrid": 6,
                        "content": "我想知道你的笔友是否已经回复你的电子邮件。<br/> I wonder ___________________________________ to your e-mail.",
                        "difficulty": 5,
                        "grade": "初中二年级",
                        "iid": 6,
                        "source": "21世纪教育在线题库",
                        "type": "翻译",
                        "uid": 3
                    },
                    "item_kps": [{
                        "degree": 5,
                        "iid": 6,
                        "kpid": 12
                    }, {
                        "degree": 5,
                        "iid": 6,
                        "kpid": 13
                    }],
                    "kPPath": "英语/现在完成时,英语/宾语从句",
                    "knowledgepoints": [{
                        "depth": 2,
                        "knowledgepoint": "宾语从句",
                        "kpid": 12,
                        "prepoint": 3
                    }, {
                        "depth": 2,
                        "knowledgepoint": "现在完成时",
                        "kpid": 13,
                        "prepoint": 3
                    }],
                    "user": {
                        "authority": "录入组卷员",
                        "name": "翠花",
                        "uid": 3
                    }
                }, {
                    "@type": "com.jinbang.model.Item_Asr_Usr_IK_Kp",
                    "answer": {
                        "asrid": 7,
                        "content": "is actively taking action(s) to"
                    },
                    "item": {
                        "asrid": 7,
                        "content": "据报道，近来日本政府正积极采取措施减少核泄漏(nuclear leak)。<br/> It is reported that the Japanese government ______________reduce the nuclear leak recently.",
                        "difficulty": 5,
                        "grade": "初中二年级",
                        "iid": 7,
                        "source": "21世纪教育在线题库",
                        "type": "翻译",
                        "uid": 2
                    },
                    "item_kps": [{
                        "degree": 5,
                        "iid": 7,
                        "kpid": 14
                    }],
                    "kPPath": "英语/现在进行时",
                    "knowledgepoints": [{
                        "depth": 2,
                        "knowledgepoint": "现在进行时",
                        "kpid": 14,
                        "prepoint": 3
                    }],
                    "user": {
                        "authority": "录入组卷员",
                        "name": "铁柱",
                        "uid": 2
                    }
                }, {
                    "@type": "com.jinbang.model.Item_Asr_Usr_IK_Kp",
                    "answer": {
                        "asrid": 8,
                        "content": "(to be) one of the most popular programs"
                    },
                    "item": {
                        "asrid": 8,
                        "content": "“中国达人秀”被认为是目前最受欢迎的电视节目之一。<br/> China’s Got Talent is considered _____________________________ these days.",
                        "difficulty": 5,
                        "grade": "初中二年级",
                        "iid": 8,
                        "source": "21世纪教育在线题库",
                        "type": "翻译",
                        "uid": 2
                    },
                    "item_kps": [{
                        "degree": 5,
                        "iid": 8,
                        "kpid": 15
                    }],
                    "kPPath": "英语/最高级",
                    "knowledgepoints": [{
                        "depth": 2,
                        "knowledgepoint": "最高级",
                        "kpid": 15,
                        "prepoint": 3
                    }],
                    "user": {
                        "authority": "录入组卷员",
                        "name": "铁柱",
                        "uid": 2
                    }
                }, {
                    "@type": "com.jinbang.model.Item_Asr_Usr_IK_Kp",
                    "answer": {
                        "asrid": 9,
                        "content": "out of breath"
                    },
                    "item": {
                        "asrid": 9,
                        "content": "Daniel气喘吁吁地赶到了，却发现他跑错了电影院。<br/> Daniel arrived _______________, but he found he had gone to the wrong cinema.",
                        "difficulty": 5,
                        "grade": "高中一年级",
                        "iid": 9,
                        "source": "21世纪教育在线题库",
                        "type": "翻译",
                        "uid": 2
                    },
                    "item_kps": [{
                        "degree": 5,
                        "iid": 9,
                        "kpid": 16
                    }],
                    "kPPath": "英语/过去完成时",
                    "knowledgepoints": [{
                        "depth": 2,
                        "knowledgepoint": "过去完成时",
                        "kpid": 16,
                        "prepoint": 3
                    }],
                    "user": {
                        "authority": "录入组卷员",
                        "name": "铁柱",
                        "uid": 2
                    }
                }, {
                    "@type": "com.jinbang.model.Item_Asr_Usr_IK_Kp",
                    "answer": {
                        "asrid": 10,
                        "content": "should be paid to"
                    },
                    "item": {
                        "asrid": 10,
                        "content": "公共场合应该注意行为举止。<br/> Attention ________________________ one’s behavior in public.",
                        "difficulty": 5,
                        "grade": "高中一年级",
                        "iid": 10,
                        "source": "21世纪教育在线题库",
                        "type": "翻译",
                        "uid": 2
                    },
                    "item_kps": [{
                        "degree": 5,
                        "iid": 10,
                        "kpid": 18
                    }],
                    "kPPath": "英语/情态动词/should",
                    "knowledgepoints": [{
                        "depth": 3,
                        "knowledgepoint": "should",
                        "kpid": 18,
                        "prepoint": 17
                    }],
                    "user": {
                        "authority": "录入组卷员",
                        "name": "铁柱",
                        "uid": 2
                    }
                }, {
                    "@type": "com.jinbang.model.Item_Asr_Usr_IK_Kp",
                    "answer": {
                        "asrid": 11,
                        "content": "enough sleep because of"
                    },
                    "item": {
                        "asrid": 11,
                        "content": "现在许多青少年由于过重的作业负担而得不到充足的睡眠。<br/> Many teenagers can’t have ______________________________too much homework.",
                        "difficulty": 5,
                        "grade": "初中一年级",
                        "iid": 11,
                        "source": "21世纪教育在线题库",
                        "type": "翻译",
                        "uid": 2
                    },
                    "item_kps": [{
                        "degree": 5,
                        "iid": 11,
                        "kpid": 19
                    }],
                    "kPPath": "英语/固定短语",
                    "knowledgepoints": [{
                        "depth": 2,
                        "knowledgepoint": "固定短语",
                        "kpid": 19,
                        "prepoint": 3
                    }],
                    "user": {
                        "authority": "录入组卷员",
                        "name": "铁柱",
                        "uid": 2
                    }
                }]
            }
        }
    }, {
        "条件搜索试题，通过单选按钮实现": {
            "method": "post",
            "data": {
                "type": "翻译",
                "grade": "高中三年级",
                "source": "上海财经大学附属中学2016年9月月考",
                "name": "建国"
            },
            "url": "http://11pm.top:8081/jinbang/itemchoose",
            "FailResponse": {
                "@type": "java.util.HashMap",
                "err": "Not Loged!"
            },
            "SuccessResponse": {
                "@type": "java.util.HashMap",
                "itemchoose": [{
                    "@type": "com.jinbang.model.Item_Asr_Usr_IK_Kp",
                    "answer": {
                        "asrid": 2,
                        "content": "By the time this railroad has been extended to the remote small city, the local economy is sure to develop/be developed very fast."
                    },
                    "item": {
                        "asrid": 2,
                        "content": "等这条铁路延伸至那座偏远的小城之后，当地的经济一定会得到迅猛的发展。（by the time）",
                        "difficulty": 5,
                        "grade": "高中三年级",
                        "iid": 2,
                        "source": "上海财经大学附属中学2016年9月月考",
                        "type": "翻译",
                        "uid": 1
                    },
                    "item_kps": [{
                        "degree": 5,
                        "iid": 2,
                        "kpid": 5
                    }],
                    "kPPath": "英语/时态/将来进行时",
                    "knowledgepoints": [{
                        "depth": 3,
                        "knowledgepoint": "将来进行时",
                        "kpid": 5,
                        "prepoint": 4
                    }],
                    "user": {
                        "authority": "录入组卷员",
                        "name": "建国",
                        "uid": 1
                    }
                }]
            }  
        }
    }, {
        "条件搜索试题，只利用其中部分的条件，例如本例中只输入 type, 支持输入上述第 5 项请求 4 个条件参数中的 [0-4] 任意个": {
            "method": "post",
            "data": {
                "type": "翻译"
            },
            "url": "http://11pm.top:8081/jinbang/itemchoose",
            "FailResponse": {
                "@type": "java.util.HashMap",
                "err": "Not Loged!"
            },
            "SuccessResponse": {
                "@type": "java.util.HashMap",
                "itemchoose": [{
                    "@type": "com.jinbang.model.Item_Asr_Usr_IK_Kp",
                    "answer": {
                        "asrid": 2,
                        "content": "By the time this railroad has been extended to the remote small city, the local economy is sure to develop/be developed very fast."
                    },
                    "item": {
                        "asrid": 2,
                        "content": "等这条铁路延伸至那座偏远的小城之后，当地的经济一定会得到迅猛的发展。（by the time）",
                        "difficulty": 5,
                        "grade": "高中三年级",
                        "iid": 2,
                        "source": "上海财经大学附属中学2016年9月月考",
                        "type": "翻译",
                        "uid": 1
                    },
                    "item_kps": [{
                        "degree": 5,
                        "iid": 2,
                        "kpid": 5
                    }],
                    "kPPath": "英语/时态/将来进行时",
                    "knowledgepoints": [{
                        "depth": 3,
                        "knowledgepoint": "将来进行时",
                        "kpid": 5,
                        "prepoint": 4
                    }],
                    "user": {
                        "authority": "录入组卷员",
                        "name": "建国",
                        "uid": 1
                    }
                }, {
                    "@type": "com.jinbang.model.Item_Asr_Usr_IK_Kp",
                    "answer": {
                        "asrid": 6,
                        "content": "if your pen friend has replied"
                    },
                    "item": {
                        "asrid": 6,
                        "content": "我想知道你的笔友是否已经回复你的电子邮件。<br/> I wonder ___________________________________ to your e-mail.",
                        "difficulty": 5,
                        "grade": "初中二年级",
                        "iid": 6,
                        "source": "21世纪教育在线题库",
                        "type": "翻译",
                        "uid": 3
                    },
                    "item_kps": [{
                        "degree": 5,
                        "iid": 6,
                        "kpid": 12
                    }, {
                        "degree": 5,
                        "iid": 6,
                        "kpid": 13
                    }],
                    "kPPath": "英语/现在完成时,英语/宾语从句",
                    "knowledgepoints": [{
                        "depth": 2,
                        "knowledgepoint": "宾语从句",
                        "kpid": 12,
                        "prepoint": 3
                    }, {
                        "depth": 2,
                        "knowledgepoint": "现在完成时",
                        "kpid": 13,
                        "prepoint": 3
                    }],
                    "user": {
                        "authority": "录入组卷员",
                        "name": "翠花",
                        "uid": 3
                    }
                }, {
                    "@type": "com.jinbang.model.Item_Asr_Usr_IK_Kp",
                    "answer": {
                        "asrid": 7,
                        "content": "is actively taking action(s) to"
                    },
                    "item": {
                        "asrid": 7,
                        "content": "据报道，近来日本政府正积极采取措施减少核泄漏(nuclear leak)。<br/> It is reported that the Japanese government ______________reduce the nuclear leak recently.",
                        "difficulty": 5,
                        "grade": "初中二年级",
                        "iid": 7,
                        "source": "21世纪教育在线题库",
                        "type": "翻译",
                        "uid": 2
                    },
                    "item_kps": [{
                        "degree": 5,
                        "iid": 7,
                        "kpid": 14
                    }],
                    "kPPath": "英语/现在进行时",
                    "knowledgepoints": [{
                        "depth": 2,
                        "knowledgepoint": "现在进行时",
                        "kpid": 14,
                        "prepoint": 3
                    }],
                    "user": {
                        "authority": "录入组卷员",
                        "name": "铁柱",
                        "uid": 2
                    }
                }, {
                    "@type": "com.jinbang.model.Item_Asr_Usr_IK_Kp",
                    "answer": {
                        "asrid": 8,
                        "content": "(to be) one of the most popular programs"
                    },
                    "item": {
                        "asrid": 8,
                        "content": "“中国达人秀”被认为是目前最受欢迎的电视节目之一。<br/> China’s Got Talent is considered _____________________________ these days.",
                        "difficulty": 5,
                        "grade": "初中二年级",
                        "iid": 8,
                        "source": "21世纪教育在线题库",
                        "type": "翻译",
                        "uid": 2
                    },
                    "item_kps": [{
                        "degree": 5,
                        "iid": 8,
                        "kpid": 15
                    }],
                    "kPPath": "英语/最高级",
                    "knowledgepoints": [{
                        "depth": 2,
                        "knowledgepoint": "最高级",
                        "kpid": 15,
                        "prepoint": 3
                    }],
                    "user": {
                        "authority": "录入组卷员",
                        "name": "铁柱",
                        "uid": 2
                    }
                }, {
                    "@type": "com.jinbang.model.Item_Asr_Usr_IK_Kp",
                    "answer": {
                        "asrid": 9,
                        "content": "out of breath"
                    },
                    "item": {
                        "asrid": 9,
                        "content": "Daniel气喘吁吁地赶到了，却发现他跑错了电影院。<br/> Daniel arrived _______________, but he found he had gone to the wrong cinema.",
                        "difficulty": 5,
                        "grade": "高中一年级",
                        "iid": 9,
                        "source": "21世纪教育在线题库",
                        "type": "翻译",
                        "uid": 2
                    },
                    "item_kps": [{
                        "degree": 5,
                        "iid": 9,
                        "kpid": 16
                    }],
                    "kPPath": "英语/过去完成时",
                    "knowledgepoints": [{
                        "depth": 2,
                        "knowledgepoint": "过去完成时",
                        "kpid": 16,
                        "prepoint": 3
                    }],
                    "user": {
                        "authority": "录入组卷员",
                        "name": "铁柱",
                        "uid": 2
                    }
                }, {
                    "@type": "com.jinbang.model.Item_Asr_Usr_IK_Kp",
                    "answer": {
                        "asrid": 10,
                        "content": "should be paid to"
                    },
                    "item": {
                        "asrid": 10,
                        "content": "公共场合应该注意行为举止。<br/> Attention ________________________ one’s behavior in public.",
                        "difficulty": 5,
                        "grade": "高中一年级",
                        "iid": 10,
                        "source": "21世纪教育在线题库",
                        "type": "翻译",
                        "uid": 2
                    },
                    "item_kps": [{
                        "degree": 5,
                        "iid": 10,
                        "kpid": 18
                    }],
                    "kPPath": "英语/情态动词/should",
                    "knowledgepoints": [{
                        "depth": 3,
                        "knowledgepoint": "should",
                        "kpid": 18,
                        "prepoint": 17
                    }],
                    "user": {
                        "authority": "录入组卷员",
                        "name": "铁柱",
                        "uid": 2
                    }
                }, {
                    "@type": "com.jinbang.model.Item_Asr_Usr_IK_Kp",
                    "answer": {
                        "asrid": 11,
                        "content": "enough sleep because of"
                    },
                    "item": {
                        "asrid": 11,
                        "content": "现在许多青少年由于过重的作业负担而得不到充足的睡眠。<br/> Many teenagers can’t have ______________________________too much homework.",
                        "difficulty": 5,
                        "grade": "初中一年级",
                        "iid": 11,
                        "source": "21世纪教育在线题库",
                        "type": "翻译",
                        "uid": 2
                    },
                    "item_kps": [{
                        "degree": 5,
                        "iid": 11,
                        "kpid": 19
                    }],
                    "kPPath": "英语/固定短语",
                    "knowledgepoints": [{
                        "depth": 2,
                        "knowledgepoint": "固定短语",
                        "kpid": 19,
                        "prepoint": 3
                    }],
                    "user": {
                        "authority": "录入组卷员",
                        "name": "铁柱",
                        "uid": 2
                    }
                }]
            }
        }
    }, {
        "按 Iid 数组删除 题目": {
            "method": "delete",
            "data": {
                "iids": [
                    1, 2, 3
                ]
            },
            "url": "http://11pm.top:8081/jinbang/itemDeleteByIids",
            "FailResponse": {
                "@type": "java.util.HashMap",
                "err": "Not Loged!"
            },
            "SuccessResponse": {
                "@type":"com.alibaba.fastjson.JSONObject",
                "Affected answer rows":1,
                "Affected item rows":1,
                "Affected item_kp rows":0,
                "Affected buildpaper rows":0
            }
        }
    }, {
        "获取当前结点下的剩余树杈": {
            "method": "get/post",
            "data": {
                "node": "电学"
            }, 
            "url": "http://11pm.top:8081/jinbang/getRestBranch",
            "FailResponse": {
                "@type": "java.util.HashMap",
                "err": "Wrong pwd! / Name not exists!"
            },
            "SuccessResponse": {
                "@type": "com.alibaba.fastjson.JSONObject",
                "将来进行时": []
            }
        }
    }, {
        "将当前路径中各结点加入树的合适位置": {
            "method": "post",
            "data": {
                "path": "化学/无机化学/氧化还原反应"
            }, 
            "url": "http://11pm.top:8081/jinbang/addKpByPath",
            "FailResponse": "Not Logged!",
            "SuccessResponse": "Knowledgepoints Added!"
        }
    }, {
        "深度编辑题目": {
            "method": "post",
            "data": {
                "old":{
                    "answer":{
                        "asrid":2,
                        "content":"By the time this railroad has been extended to the remote small city, the local economy is sure to develop/be developed very fast."
                    },
                    "item":{
                        "asrid":2,
                        "content":"等这条铁路延伸至那座偏远的小城之后，当地的经济一定会得到迅猛的发展。（by the time）",
                        "difficulty":5,
                        "grade":"高中三年级",
                        "iid":2,
                        "source":"上海财经大学附属中学2016年9月月考",
                        "type":"翻译",
                        "uid":1
                    },
                    "item_kps":[
                        {
                            "degree":5,
                            "iid":2,
                            "kpid":5
                        }
                    ],
                    "knowledgepoints":[
                        {
                            "depth":3,
                            "knowledgepoint":"将来进行时",
                            "kpid":5,
                            "prepoint":4
                        }
                    ],
                    "user":{
                        "authority":"录入组卷员",
                        "name":"建国",
                        "uid":1
                    }
                },
                "new":{
                    "answer":{
                        "asrid":2,
                        "content":"By the time this railroad has been extended to the remote small city, the local economy is sure to develop/be developed very fast."
                    },
                    "item":{
                        "asrid":2,
                        "content":"等这条铁路延伸至那座偏远的小城之后，当地的经济一定会得到迅猛的发展。（by the time）",
                        "difficulty":5,
                        "grade":"高中三年级",
                        "iid":2,
                        "source":"上海财经大学附属中学2016年9月月考",
                        "type":"翻译",
                        "uid":1
                    },
                    "item_kps":[
                        {
                            "degree":5,
                            "iid":2,
                            "kpid":5
                        }
                    ],
                    "knowledgepoints":[
                        {
                            "depth":3,
                            "knowledgepoint":"将来进行时",
                            "kpid":5,
                            "prepoint":4
                        }
                    ],
                    "user":{
                        "authority":"录入组卷员",
                        "name":"建国",
                        "uid":1
                    }
                }
            }, 
            "url": "http://11pm.top:8081/jinbang/editItemFully",
            "FailResponse": {
                "@type": "com.alibaba.fastjson.JSONObject",
                "Err!": "Not Logged!"
            },
            "SuccessResponse": {
                "@type": "com.alibaba.fastjson.JSONObject",
                "Success!": "Edit fully!"
            }
        }
    }, {
        "深度增加题目，并查重": {
            "method": "post",
            "data": {
                "answer":{
                    "asrid":2,
                    "content":"By the time this railroad has been extended to the remote small city, the local economy is sure to develop/be developed very fast."
                },
                "item":{
                    "asrid":2,
                    "content":"等这条铁路延伸至那座偏远的小城之后，当地的经济一定会得到迅猛的发展。（by the time）",
                    "difficulty":5,
                    "grade":"高中三年级",
                    "iid":2,
                    "source":"上海财经大学附属中学2016年9月月考",
                    "type":"翻译",
                    "uid":1
                },
                "item_kps":[
                    {
                        "degree":5,
                        "iid":2,
                        "kpid":5
                    }
                ],
                "knowledgepoints":[
                    {
                        "depth":3,
                        "knowledgepoint":"将来进行时",
                        "kpid":5,
                        "prepoint":4
                    }
                ],
                "user":{
                    "authority":"录入组卷员",
                    "name":"建国",
                    "uid":1
                }
            }, 
            "url": "http://11pm.top:8081/jinbang/addItemFully",
            "FailResponse": {
                "@type": "com.alibaba.fastjson.JSONObject",
                "Err!": "Not Logged!"
            },
            "SuccessResponse": {
                "@type": "com.alibaba.fastjson.JSONObject",
                "Success!": "Add fully!"
            }
        }
    }, {
        "用户注销": {
            "method": "get/post",
            "url": "http://11pm.top:8081/jinbang/signout",
            "response": {
                "@type": "java.util.HashMap",
                "success": "Logged out!"
            }
        }
    }
]}
