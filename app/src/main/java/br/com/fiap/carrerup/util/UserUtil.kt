package br.com.fiap.carrerup.util

import br.com.fiap.carrerup.enums.Areas

val descriptions: Map<String, String> = mapOf(
    Areas.TI.label to "Profissional de TI com experiência em APIs, sistemas complexos e testes. Focado em qualidade, automação e soluções eficientes para desafios tecnológicos.",
    Areas.SAUDE.label to "Profissional da saúde com experiência em atendimento clínico, diagnóstico e cuidado ao paciente. Focado em bem-estar, eficiência e melhoria contínua dos serviços de saúde.",
    Areas.FINANCAS.label to "Profissional de finanças com experiência em análise de dados, planejamento financeiro e gestão de investimentos. Focado em estratégias para otimização de resultados e redução de riscos.",
    Areas.MARKETING.label to "Profissional de marketing com experiência em estratégias digitais, branding e campanhas publicitárias. Focado em aumentar a visibilidade da marca e engajamento com o público-alvo.",
    Areas.ENGENHARIA.label to "Engenheiro com experiência em projetos, desenvolvimento e otimização de processos. Focado em soluções inovadoras, eficiência e sustentabilidade no setor industrial e tecnológico."
)

fun getDescription(key: String) : String {
    return descriptions[key] ?: "Descrição não encontrada."
}